package com.clomez.invalane.services;

import com.clomez.invalane.beans.Options;

import java.util.List;


public interface OptionService {

    void save(Options option);

    public List<Options> getOptions();

    public Options getOption(Long id);

}
