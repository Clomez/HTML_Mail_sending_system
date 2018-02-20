package com.clomez.invalane.beans;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Options {

    @Id
    @GeneratedValue
    Long id;

    String to;

    String name;

    String from;
    String username;
    String pass;

    String host;

    String content;

    String emailList;

    public Options() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmailList() {
        return emailList;
    }

    public void setEmailList(String emailList) {
        this.emailList = emailList;
    }

    public Options(String to, String name, String from, String username, String pass, String host, String content, String emailList) {

        this.to = to;
        this.name = name;
        this.from = from;
        this.username = username;
        this.pass = pass;
        this.host = host;
        this.content = content;
        this.emailList = emailList;
    }
}
