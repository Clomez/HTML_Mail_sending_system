package com.clomez.invalane.controller;

import com.clomez.invalane.beans.Options;
import com.clomez.invalane.repositories.OptionsRepository;
import com.clomez.invalane.services.EmailService;
import com.clomez.invalane.services.OptionService;
import com.clomez.invalane.services.ReceiverService;
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

    @Autowired
    ReceiverService receiverService;

    @Autowired
    OptionService optionService;

    @Autowired
    OptionsRepository optionsRepository;


    // SETTINGS AND HOLDERS
    private static String UPLOADED_FOLDER = "src/main/resources/emails/";
    private static String RECEIVER_FOLDER = "src/main/resources/receivers/";
    private String fileNameHolder;
    private String optionHolder;

    //EMAIL ATTRIBUTES
    List<Options> options;
    List<String> list;
    String fromo;

    @RequestMapping("/")
    public String home() {

        return "index";
    }

    /////////////////////////////////////////////
    // UPLOAD ZIP SERVICE
    /////////////////////////////////////////

    @PostMapping("/addMail")
    public String add(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes, String options, String receiver, String fromo2) {


        if (file.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Please select a file");

            return "error/settingError";
        }
        try{
            fromo = fromo2;
            optionHolder = options;
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            String name = file.getOriginalFilename();
            Files.write(path, bytes);
            fileNameHolder = file.getOriginalFilename();
            emailService.zipReader(name, UPLOADED_FOLDER, receiver, fromo);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:confirm";
    }
    /////////////////////////////////////////////
    // EMAIL SERVICE
    /////////////////////////////////////////
    @GetMapping("/send")
    public String send(Model model) {

        list = receiverService.getLists();


        try{
            options = optionService.getOptions();
            model.addAttribute("Options", options );
            model.addAttribute("receiver", list);
            model.addAttribute("fromo", fromo);
            return "sendMail/newMail";


        }catch (Exception e){
         System.out.println("Problem with options");
            return "error/settingError";
        }

    }

    @GetMapping("/confirm")
    public String confirm(Model model) {

        model.addAttribute("receiver", list);
        model.addAttribute("Options", options);
        model.addAttribute("FileName", fileNameHolder);

        try{

        }catch (Exception e) {
            System.out.println(e);
            return "redirect:send";
        }

        return "sendMail/confirm";
    }
    @PostMapping("/postMail")
    public String sendMail() {

        try{
            emailService.confirmOptions();

        }catch (UnknownError e){
            return "redirect:sendMail/error";
        }
        return "sendMail/success";
    }

    /////////////////////////////////////////////
    // RECEIVER SERVICE
    /////////////////////////////////////////
    @GetMapping("/ComposeList")
    public String compose(Model model){

        String type = null;

        model.addAttribute("type", type);

        return "makeLists";
    }
    @PostMapping("/addList")
    public String addList(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes, String type, String name) {


        if (file.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Please select a file");

            return "redirect:new";
        }
        try{
            System.out.println(type);
            byte[] bytes = file.getBytes();
            Path path = Paths.get(RECEIVER_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            fileNameHolder = file.getOriginalFilename();
            String full_name = RECEIVER_FOLDER + file.getOriginalFilename();
            receiverService.composeList(type, full_name, name);

        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }

        return "redirect:/";
    }
    /////////////////////////////////////////////
    // OPTIONS SERVICE
    /////////////////////////////////////////
    @GetMapping("/makeOptions")
    public String makeOptions(Model model) {

        Options options = new Options();
        model.addAttribute("options", options);

        return "makeOptions";
    }
    @PostMapping("/addOptions")
    public String addOptions(@ModelAttribute Options options){
        try {
            optionService.save(options);
        }catch (Exception e){
            System.out.println(e);
        }

        return "redirect:/";
    }
    /////////////////////////////////////////////
    // EMAIL SERVICE
    /////////////////////////////////////////
    @GetMapping("/error")
    public String mainError() {
        return "error/error";
    }
}
