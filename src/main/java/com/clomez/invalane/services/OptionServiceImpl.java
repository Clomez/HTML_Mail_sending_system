package com.clomez.invalane.services;

import com.clomez.invalane.beans.Options;
import com.clomez.invalane.repositories.OptionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {

    OptionsRepository repository;

    @Override
    public void save(Options options){}

    @Override
    public List<Options> getOptions() {

        Options options = new Options();

        List<Options> optionsList = (List<Options>) repository.findAll();

        return optionsList;
    }

    @Override
    public Options getOption(Long id) {

        Options option = repository.findOne(id);
        return option;
    }
}
