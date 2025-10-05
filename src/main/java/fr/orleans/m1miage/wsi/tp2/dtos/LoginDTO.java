package fr.orleans.m1miage.wsi.tp2.dtos;

public class LoginDTO {

    private String login;
    private String password;


    public String getUsername() {
        return login;
    }
    public void setUsername(String username) {
        this.login = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
