package com.clomez.invalane.services;

import com.clomez.invalane.beans.Email;
import com.clomez.invalane.beans.Options;
import com.clomez.invalane.repositories.OptionsRepository;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;


import javax.mail.internet.MimeMessage;
import javax.websocket.Session;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;

@Service
public class EmailServiceImpl implements EmailService {

    Email email = new Email();

    private JavaMailSender mailSender;
    private OptionService optionService;

    @Autowired
    public void MailService(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    public void prepareAndSend() {

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(email.getFrom());
            messageHelper.setTo(email.getTo());
            messageHelper.setSubject("subject");
            messageHelper.setText(email.getContent());
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            // runtime exception; compiler will not force you to handle it
        }
    }


    @Override
    public void newEmail(String date, String path, String zipdestinationString) {

       email.setContent(dataReader(path, zipdestinationString));

    }

    @Override
    public void setEmailAttributes(Email email) {

        email.setHost(email.getHost());
        email.setFrom(email.getFrom());
        email.setUsername(email.getUsername());
        email.setPass(email.getPass());

        email.setTo(email.getTo());

        optionService.save(email);

    }

    @Override
    public void zipReader(String name, String path){

        String zipsource = path + name;
        String date = date();
        String zipdestination = path + date;

        try {
            ZipFile zipFile = new ZipFile(zipsource);
            zipFile.extractAll(zipdestination);

        }catch (ZipException e) {
            e.printStackTrace();
        }

        newEmail(date, path, zipdestination);


    }

    @Override
    public String dataReader(String date, String zipdestinationString) {

        FileInputStream in = null;

        File folder = new File(zipdestinationString);

        File[] listFiles = folder.listFiles();

        for (File file : listFiles) {

            if (file.isFile()) {
                String Filesname = getFileExtension(file);

                if (Filesname.equals("html")) {

                    String Filename = file.getName();

                    try {
                        byte[] encoded = Files.readAllBytes(Paths.get(zipdestinationString + "/" + Filename));
                        String s = new String(encoded);
                        return s;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }else {System.out.println("no files :(");}


        }
        return "error";
    }

    @Override
    public String getFileExtension(File file) {
        String fileName = file.getName();

        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0){
            return fileName.substring(fileName.lastIndexOf(".")+1);}
        else {return "";}
    }


}

