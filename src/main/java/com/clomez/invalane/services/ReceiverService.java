package com.clomez.invalane.services;

import com.clomez.invalane.beans.Email;
import com.clomez.invalane.beans.Receiver;

import java.util.List;

public interface ReceiverService {
    void composeList();

    public List<Receiver> getList(Email email);
}
