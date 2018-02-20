package com.clomez.invalane.services;

import com.clomez.invalane.beans.Email;
import com.clomez.invalane.beans.Options;

import java.util.List;


public interface OptionService {

    void save(Email email);

    List<Options> getOptions();

}
