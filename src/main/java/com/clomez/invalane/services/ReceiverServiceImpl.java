package com.clomez.invalane.services;

import com.clomez.invalane.beans.Email;
import com.clomez.invalane.beans.Receiver;
import com.clomez.invalane.repositories.ReceiverRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiverServiceImpl implements ReceiverService{

    ReceiverRepository repository;

    @Override
    public void composeList() {

        List<Receiver> receivers;

    }

    public List<Receiver> getList(Email email){

        List<Receiver> receivers;

        receivers = (List<Receiver>) repository.findAll();

        return receivers;


    }
}
