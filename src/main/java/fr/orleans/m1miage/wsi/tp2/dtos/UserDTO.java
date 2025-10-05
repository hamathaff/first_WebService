package fr.orleans.m1miage.wsi.tp2.dtos;

public class UserDTO {
    private String username;
    private String userlogin;
    private String userid;

    public UserDTO(String username, String userlogin,String userid) {
        this.username = username;
        this.userlogin = userlogin;
        this.userid = userid;



    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserlogin() {
        return userlogin;
    }
    public void setUserlogin(String myuserlogin) {
        userlogin = myuserlogin;
    }

    public String getUserid() {
        return userid;
    }


    public void setUserid(String userid) {
        this.userid = userid;
    }
}
