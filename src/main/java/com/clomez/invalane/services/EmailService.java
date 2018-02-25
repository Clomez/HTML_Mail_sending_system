package com.clomez.invalane.services;

import com.clomez.invalane.beans.Receiver;

import java.io.File;
import java.util.List;

public interface EmailService {


    void zipReader(String name, String path, String receiver, String fromo);

    String dataReader(String path);

    String getFileExtension(File file);

    void prepareAndSend(String zipdestination, String name, String receiver);

    void confirm(List<Receiver> list, String st);

    void confirmOptions();


}
