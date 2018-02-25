package com.clomez.invalane.services;

import com.clomez.invalane.beans.Receiver;
import com.clomez.invalane.repositories.ReceiverRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                repository.save(obj);
            }

        }
        if (type.equals("CSV")) {

        }

    }

    public List<Receiver> getList(String list){

        List<Receiver> receivers;
        receivers = repository.findReceiversByList(list);

        return receivers;


    }

    @Override
    public List<String> getLists() {

        List<String> diffLists = new ArrayList<>();
        List<Receiver> list = (List<Receiver>) repository.findAll();

        Set<String> clear = new HashSet<>();

        for (Receiver e : list) {
          String test = e.getList();
          diffLists.add(test);
        }
        clear.addAll(diffLists);
        diffLists.clear();
        diffLists.addAll(clear);

        return diffLists;

    }
}
