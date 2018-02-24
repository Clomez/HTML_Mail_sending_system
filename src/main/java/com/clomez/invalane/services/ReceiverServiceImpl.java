package com.clomez.invalane.services;

import com.clomez.invalane.beans.Receiver;
import com.clomez.invalane.repositories.ReceiverRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ReceiverServiceImpl implements ReceiverService{

    @Autowired
    private ReceiverRepository repository;

    @Override
    public void composeList(String type, String full_name, String listName) throws IOException {

        if (type.equals("JSON")){

            ObjectMapper mapper = new ObjectMapper();
            List<Receiver> list = mapper.readValue(new File(full_name), new TypeReference<List<Receiver>>(){});
            for (Receiver obj : list) {
                obj.setList(listName);
                System.out.println(obj.toString());
                repository.save(obj);
            }

        }
        if (type.equals("CSV")) {

        }
        else{

        }

    }

    public List<Receiver> getList(){

        List<Receiver> receivers;

        receivers = (List<Receiver>) repository.findAll();

        return receivers;


    }
}
