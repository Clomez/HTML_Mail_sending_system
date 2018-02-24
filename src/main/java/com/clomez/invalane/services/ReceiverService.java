package com.clomez.invalane.services;

import com.clomez.invalane.beans.Email;
import com.clomez.invalane.beans.Receiver;

import java.io.IOException;
import java.util.List;

public interface ReceiverService {
    void composeList(String type, String full_name, String listName) throws IOException;

    public List<Receiver> getList();
}
