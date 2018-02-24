package com.clomez.invalane.beans;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Options {

    @Id
    @GeneratedValue
    Long id;

    private String nameo;

    private String fromo;
    private String usernameo;
    private String passo;

    private String hosto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameo() {
        return nameo;
    }

    public void setNameo(String nameo) {
        this.nameo = nameo;
    }

    public String getFromo() {
        return fromo;
    }

    public void setFromo(String fromo) {
        this.fromo = fromo;
    }

    public String getUsernameo() {
        return usernameo;
    }

    public void setUsernameo(String usernameo) {
        this.usernameo = usernameo;
    }

    public String getPasso() {
        return passo;
    }

    public void setPasso(String passo) {
        this.passo = passo;
    }

    public String getHosto() {
        return hosto;
    }

    public void setHosto(String hosto) {
        this.hosto = hosto;
    }

    public Options(String nameo, String fromo, String usernameo, String passo, String hosto) {
        this.nameo = nameo;
        this.fromo = fromo;
        this.usernameo = usernameo;
        this.passo = passo;
        this.hosto = hosto;
    }

    public Options() {

    }
}
