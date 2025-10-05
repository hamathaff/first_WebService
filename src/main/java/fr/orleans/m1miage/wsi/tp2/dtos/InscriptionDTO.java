package fr.orleans.m1miage.wsi.tp2.dtos;

import java.io.Serializable;

public class InscriptionDTO implements Serializable {
    private String username;
    private String password;
    private String mail;
    private String role;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }


}
