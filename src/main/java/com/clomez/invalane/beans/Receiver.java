package com.clomez.invalane.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Receiver {

    @Id
    @GeneratedValue
    Long id;

    String name;
    String email;
    String list;

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Receiver(String name, String email, String list) {

        this.name = name;
        this.email = email;
    }

    public Receiver() {

    }
}
