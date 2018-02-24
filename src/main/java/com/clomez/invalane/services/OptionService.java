package com.clomez.invalane.services;

import com.clomez.invalane.beans.Email;
import com.clomez.invalane.beans.Options;

import java.util.List;


public interface OptionService {

    void save(Options options);

    List<Options> getOptions();

    Options getOption(Long id);

}
