package com.clomez.invalane.services;

import com.clomez.invalane.beans.Images;

import java.util.List;

public interface ImageService {

    List imageUpload(String path, String name);
    void saveImages(List images);
    String composeHTML(String s, List<Images> list);
}
