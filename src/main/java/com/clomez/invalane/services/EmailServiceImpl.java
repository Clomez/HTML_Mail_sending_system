package com.clomez.invalane.services;

import com.clomez.invalane.beans.*;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;

@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender mailSender;

    //HOLD FROM ATTRIBUTE
    private String fromoHolder;

    //CONFIRMATION HOLDERS FROM PREPARE
    private List<Receiver> ReceiverListHolder;
    private String st;

    @Autowired
    ImageService imageService;

    @Autowired
    PersonalizeService personalizeService;

    @Autowired
    OptionService optionService;

    @Autowired
    ReceiverService receiverService;

    @Autowired
    public void MailService(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    public void prepareAndSend(String zipdestination, String name, String receiver) {

        String s = dataReader(zipdestination);
        List<Images> imagesList = new ArrayList<>();
        imagesList = imageService.imageUpload(zipdestination, name);
        st = imageService.composeHTML(s, imagesList);

       ReceiverListHolder = receiverService.getList(receiver);

    }

    public void confirmOptions() {

        personalizeService.personalize(st, ReceiverListHolder);

    }

    @Override
    public void confirm(List<Receiver> list, String st) {
        for (Receiver d : list){

            System.out.println("Fromo holder " + fromoHolder);

            MimeMessagePreparator messagePreparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                messageHelper.setFrom(fromoHolder);
                messageHelper.setTo(d.getEmail());
                messageHelper.setSubject("subject");
                messageHelper.setText(st);
            };
            try {
                mailSender.send(messagePreparator);
            } catch (MailException e) {
                System.out.println(e);
            }
        }

    }

    @Override
    public void zipReader(String name, String path, String receiver, String fromo){

        String zipsource = path + name;
        String date = date();
        String zipdestination = path + date;
        fromoHolder = fromo;

        try {
            ZipFile zipFile = new ZipFile(zipsource);
            zipFile.extractAll(zipdestination);
            prepareAndSend(zipdestination, name, receiver);

        }catch (ZipException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String dataReader(String zipdestinationString) {

        File folder = new File(zipdestinationString);

        File[] listFiles = folder.listFiles();

        assert listFiles != null;
        for (File file : listFiles) {

            if (file.isFile()) {
                String Filesname = getFileExtension(file);

                if (Filesname.equals("html")) {

                    String Filename = file.getName();

                    try {
                        byte[] encoded = Files.readAllBytes(Paths.get(zipdestinationString + "/" + Filename));
                        return new String(encoded);

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

