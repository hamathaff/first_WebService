package fr.orleans.m1miage.wsi.tp2.modele;

import fr.orleans.m1miage.wsi.tp2.dtos.PlaylisDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Playlist {
    private String name;
    private String description;
    private User creator;
    private List<Video>  myplaylist;



    public  Playlist(String playlistName, String description,User creator)
    {
        this.name = playlistName;
        this.creator = creator;
        this.description = description;
        myplaylist = new ArrayList<>();
    }

    public List<Video> getMyplaylist() {
        return myplaylist;
    }


    public void addVideo(Video video)
    {
        myplaylist.add(video);
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    public User getCreator() {
        return creator;
    }



    public String getMyPlaylistName(){
        return this.name;
    }

    public PlaylisDTO toDTO(){
        return new PlaylisDTO(this.getMyPlaylistName(), this.getDescription(), this.getCreator().getUsername(), this.creator.getUserid());
    }
}
