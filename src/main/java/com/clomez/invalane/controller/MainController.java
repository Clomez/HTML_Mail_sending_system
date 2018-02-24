package com.clomez.invalane.controller;

import com.clomez.invalane.beans.Email;
import com.clomez.invalane.beans.Options;
import com.clomez.invalane.beans.Receiver;
import com.clomez.invalane.services.EmailService;
import com.clomez.invalane.services.OptionService;
import com.clomez.invalane.services.ReceiverService;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.html.Option;
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

    Email holder = new Email();
    Options optionsHolder = new Options();

    String RList;
    String fileName;


    private static String UPLOADED_FOLDER = "/home/clomez/Documents/email/emails/";
    private static String RECEIVER_FOLDER = "/home/clomez/Documents/email/receivers/";


    @RequestMapping("/")
    public String home() {

        return "index";
    }

    /////////////////////////////////////////////
    // UPLOAD ZIP SERVICE
    /////////////////////////////////////////

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

        return "redirect:confirm";
    }
    /////////////////////////////////////////////
    // EMAIL SERVICE
    /////////////////////////////////////////
    @GetMapping("/send")
    public String send(Model model) {

        List<Options> options;
        List<Receiver> list;

        receiverService.getList();

        try{
            options = optionService.getOptions();
            model.addAttribute("Options", options );
            model.addAttribute("option", optionsHolder);

        }catch (NullPointerException e){
         System.out.println("There was no options");

            model.addAttribute("email", new Email());
            model.addAttribute("option", optionsHolder);
            return "sendMail/newMail";
        }

        model.addAttribute("email", new Email());


        return "sendMail/newMail";
    }

    @GetMapping("/confirm")
    public String confirm(Model model) {

        model.addAttribute("Holder", holder);
        model.addAttribute("RecepientList", RList);
        model.addAttribute("FileName", fileName);

        try{

        }catch (NullPointerException e) {
            System.out.println("There was no attributes");
            return "redirect:send";
        }


        return "sendMail/confirm";
    }
    @PostMapping("/postMail")
    public String sendMail(@ModelAttribute Options option) {

        try{
            emailService.setEmailAttributes(optionService.getOption(option.getId()));

            emailService.prepareAndSend();
        }catch (UnknownError e){
            return "redirect:sendMail/error";
        }
        return "/";
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
            fileName = file.getOriginalFilename();
            String full_name = RECEIVER_FOLDER + file.getOriginalFilename();
            receiverService.composeList(type, full_name, name);

        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }

        return "redirect:confirm";
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
}
