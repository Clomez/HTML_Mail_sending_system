package com.clomez.invalane.services;

import com.clomez.invalane.beans.Email;
import com.clomez.invalane.beans.Options;

import java.io.File;
import java.nio.file.Path;

public interface EmailService {


    void zipReader(String name, String path);

    String dataReader(String path);

    String getFileExtension(File file);

    void setEmailAttributes(Options options);

    void prepareAndSend();


}
