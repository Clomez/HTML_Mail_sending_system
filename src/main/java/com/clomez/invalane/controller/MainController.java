package com.clomez.invalane.controller;

import com.clomez.invalane.beans.Email;
import com.clomez.invalane.beans.Options;
import com.clomez.invalane.services.EmailService;
import com.clomez.invalane.services.OptionService;
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
import java.util.List;

@Controller
public class MainController {

    @Autowired
    EmailService emailService;

    Email holder = new Email();

    String RList;
    String fileName;

    private OptionService optionService;

    private static String UPLOADED_FOLDER = "/home/clomez/Documents/email/";


    @RequestMapping("/")
    public String home() {

        return "index";
    }

    @GetMapping("/new")
    public String newMail() {

        return "sendMail/newMail";
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
            fileName = file.getOriginalFilename();
            emailService.zipReader(name, UPLOADED_FOLDER);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:send";
    }
    @GetMapping("/send")
    public String send(Model model) {
        List<Options> options;

        try{
            options = optionService.getOptions();
            model.addAttribute("Options", options );

        }catch (NullPointerException e){
         System.out.println("There was no options");
        }

        model.addAttribute("Email", new Email());


        return "sendMail/addAttributes";
    }

    @PostMapping("/addAttributes")
    public String addAttributes(@ModelAttribute Email email) {

        try{
            emailService.setEmailAttributes(email);
        }catch (NullPointerException e) {
            System.out.println("There was no attributes");
            return "redirect:send";
        }

        holder = email;

        return "redirect:confirm";
    }

    @GetMapping("/confirm")
    public String confirm(Model model) {

        model.addAttribute("Holder", holder);
        model.addAttribute("RecepientList", RList);
        model.addAttribute("FileName", fileName);

        return "sendMail/confirm";
    }
    @PostMapping("/postMail")
    public String sendMail() {

        try{
            emailService.prepareAndSend();
        }catch (UnknownError e){
            return "redirect:sendMail/error";
        }
        return "/";
    }

}
