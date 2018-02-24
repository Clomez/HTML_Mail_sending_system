package com.clomez.invalane.services;

import com.clomez.invalane.beans.Options;
import com.clomez.invalane.repositories.OptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {

    @Autowired
    private OptionsRepository repository;

    @Override
    public void save(Options options){
        System.out.println(options.toString());
        Options optionsHolder = options;
        optionsHolder.setNameo("test");
        optionsHolder.setId(0L);
        System.out.println(optionsHolder.toString());
        repository.save(options);

    }

    @Override
    public List<Options> getOptions() {

        List<Options> optionsList = (List<Options>) repository.findAll();

        return optionsList;
    }
    @Override
    public Options getOption(Long id) {

        Options options = new Options();

        repository.findOne(id);

        return options;
    }


}
