package com.clomez.invalane.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Images {

    @Id
    @GeneratedValue
    Long id;

    String originalName;
    String newName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public Images() {

    }

    public Images(String originalName, String newName) {

        this.originalName = originalName;
        this.newName = newName;
    }
}
