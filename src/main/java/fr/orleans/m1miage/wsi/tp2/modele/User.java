package fr.orleans.m1miage.wsi.tp2.modele;

import fr.orleans.m1miage.wsi.tp2.dtos.UserDTO;
import org.springframework.beans.propertyeditors.UUIDEditor;

import java.util.UUID;

public abstract class User {
    private  String username;
    private String password;
    private String userid;
    private String email;


    public User(String username, String mail ,String password) {
        this.username = username;
        this.password = password;
        this.userid = UUID.randomUUID().toString();
        this.email = mail;
    }





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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public static UserDTO toUserDTO(User user){
        return  new UserDTO(user.getUsername(),user.getEmail(),user.getUserid());
    }
}
