package com.clomez.invalane.services;

import com.clomez.invalane.beans.Email;

import java.io.File;
import java.nio.file.Path;

public interface EmailService {

    void newEmail(String date, String path, String zipdestination);

    void zipReader(String name, String path);

    String dataReader(String date, String path);

    String getFileExtension(File file);

    void setEmailAttributes(Email email);


}
