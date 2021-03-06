package com.clomez.invalane.services;

import com.clomez.invalane.beans.Images;
import com.clomez.invalane.repositories.ImagesRepository;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    EmailService emailService;

    @Autowired
    ImagesRepository imagesRepository;

    //TO FIX UPLOAD PATHS
    private String currRng;

    //MAILUSER
    String basePath = "/home/mailuser/public_html/upload/";

    @Override
    public void saveImages(List images) {
        imagesRepository.save(images);

    }

    @Override
    public String composeHTML(String s, List<Images> list) {

        for (Images images : list){
            String old = "img/" + images.getOriginalName();
            String newst = "localhost/~mailuser/upload/" + currRng + "/" + images.getNewName();
            s = s.replaceAll(old, newst);
            System.out.println(newst);
        }

        return s;

    }


    @Override
    public List imageUpload(String path, String name) {

        int randomNum = ThreadLocalRandom.current().nextInt(1, 10000 + 1);
        String DIR_NAME =  String.valueOf(randomNum);
        currRng = DIR_NAME;
        String FULL_DIR = basePath + DIR_NAME;
        File dir = new File(FULL_DIR);
        dir.mkdir();

        path = path + "/img/";
        String UPLOAD_PATH = basePath  + DIR_NAME + "/";

        String imgName = "images";
        int count = 0;

        File folder = new File(path);

        File[] listFiles = folder.listFiles();

        List<Images> imagesList = new ArrayList<>();


        for (File file : listFiles) {

            if (file.isFile()) {
                String Filesname = emailService.getFileExtension(file);
                String originalName = file.getName();

                if(Filesname.equals("png")){
                    try {
                        file.renameTo(new File(UPLOAD_PATH + imgName + count + ".png"));
                        imagesList.add(new Images(originalName, imgName + count + ".png"));
                        count++;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if(Filesname.equals("jpg")){
                    try{
                        Images images = new Images();
                        file.renameTo(new File(UPLOAD_PATH + imgName + count + ".jpg"));
                        imagesList.add(new Images(originalName,imgName + count + ".jpg"));
                        count++;
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                if(Filesname.equals("gif")){
                    try{
                        Images images = new Images();
                        file.renameTo(new File(UPLOAD_PATH + imgName + count + ".gif"));
                        imagesList.add(new Images(originalName,imgName + count + ".gif"));
                        count++;
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }else {System.out.println("no files :(");}


        }
        saveImages(imagesList);
        return imagesList;

    }
}
