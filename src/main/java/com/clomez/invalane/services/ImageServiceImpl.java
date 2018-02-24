package com.clomez.invalane.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    EmailService emailService;

    @Override
    public void imageUpload(String path) {

        path = path + "/img/";
        String UPLOAD_PATH = "src/main/resources/uploads/";
        String imgName = "images";
        int count = 0;

        FileInputStream in = null;

        File folder = new File(path);

        File[] listFiles = folder.listFiles();

        for (File file : listFiles) {

            if (file.isFile()) {
                String Filesname = emailService.getFileExtension(file);

                if(Filesname.equals("png")){
                    try {
                        file.renameTo(new File(UPLOAD_PATH + imgName + count + ".png"));
                        System.out.println(UPLOAD_PATH + imgName + count + ".png");
                        count++;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if(Filesname.equals("jpg")){
                    try{
                        file.renameTo(new File(UPLOAD_PATH + imgName + count + ".jpg"));
                        System.out.println(UPLOAD_PATH + imgName + count + ".jpg");
                        count++;
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }else {System.out.println("no files :(");}


        }

    }
}
