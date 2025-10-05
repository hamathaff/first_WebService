package fr.orleans.m1miage.wsi.tp2.dtos;

public class PlaylisDTO {
    private String name;
    private String description;
    private String userid;
    private String username;


    public PlaylisDTO(String name, String description, String username,String userid) {
        this.name = name;
        this.description = description;
        this.userid = userid;
        this.username = username;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
