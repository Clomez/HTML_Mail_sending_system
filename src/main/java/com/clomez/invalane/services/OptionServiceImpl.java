package com.clomez.invalane.services;

import com.clomez.invalane.beans.Email;
import com.clomez.invalane.beans.Options;
import com.clomez.invalane.repositories.OptionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {

    OptionsRepository repository;

    @Override
    public void save(Email email){



    }

    @Override
    public List<Options> getOptions() {

        Options options = new Options();

        List<Options> optionsList = (List<Options>) repository.findAll();

        return optionsList;
    }


}
