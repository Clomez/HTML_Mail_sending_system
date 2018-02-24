package com.clomez.invalane.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ContentHolder {

    @Id
    @GeneratedValue
    Long id;

    String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ContentHolder() {

    }

    public ContentHolder(String content) {

        this.content = content;
    }
}
