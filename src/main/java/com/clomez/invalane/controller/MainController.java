package com.clomez.invalane.controller;

import com.clomez.invalane.beans.Email;
import com.clomez.invalane.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class MainController {

    @Autowired
    EmailService emailService;

    Email holder = new Email();

    private static String UPLOADED_FOLDER = "/home/clomez/Documents/email/";


    @RequestMapping("/")
    public String home() {

        return "index";
    }

    @GetMapping("/new")
    public String newMail() {

        return "newMail";
    }
    @PostMapping("/addMail")
    public String add(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes) {

        if (file.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Please select a file");

            return "redirect:new";
        }
        try{

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            String name = file.getOriginalFilename();
            Files.write(path, bytes);
            emailService.zipReader(name, UPLOADED_FOLDER);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:send";
    }
    @GetMapping("/send")
    public String send(Model model) {

        model.addAttribute("Email", new Email());

        return "addAttributes";
    }

    @PostMapping("/addAttributes")
    public String addAttributes(@ModelAttribute Email email) {

        emailService.setEmailAttributes(email);

        return "confirm";
    }

}
