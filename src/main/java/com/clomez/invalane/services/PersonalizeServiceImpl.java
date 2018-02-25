package com.clomez.invalane.services;

import com.clomez.invalane.beans.Receiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonalizeServiceImpl implements PersonalizeService {

    @Autowired
    EmailService emailService;

    @Override
    public void personalize(String st, List<Receiver> list) {
    for (Receiver e : list) {
        String name = e.getName();

        st = st.replaceAll("!-insert.name",name);
        System.out.println("Perzonal: " + name);


        emailService.confirm(list, st);
    }
    }


}
