package fr.orleans.m1miage.wsi.tp2.facade;

import fr.orleans.m1miage.wsi.tp2.dtos.InscriptionDTO;
import fr.orleans.m1miage.wsi.tp2.dtos.PlaylisDTO;
import fr.orleans.m1miage.wsi.tp2.dtos.UserDTO;
import fr.orleans.m1miage.wsi.tp2.exceptions.DonneesIncorrectException;
import fr.orleans.m1miage.wsi.tp2.exceptions.EmailDejaPrisException;
import fr.orleans.m1miage.wsi.tp2.exceptions.UserNotFoundException;
import fr.orleans.m1miage.wsi.tp2.modele.Admin;
import fr.orleans.m1miage.wsi.tp2.modele.ClassicalUser;
import fr.orleans.m1miage.wsi.tp2.modele.Playlist;
import fr.orleans.m1miage.wsi.tp2.modele.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @ component = dire a spring de l'instancier une seul fois et aussi bean(pour les methodes)**/
@Component

public class FacadePlaylist {
    Map<String, User> users;
    Map<String, Playlist> playlists;

    public FacadePlaylist(){
        this.users = new HashMap<>();
        this.playlists = new HashMap<>();
    }
/**
 * Methode permettant d'enregistrer un nouveau user
 * @param inscriptiondto : les valeurs des variables envoyées par le client pour l'enregistrement d'un utilisareur
 * @return : retourne l'objet representant les informations qui seront affichées pour represneter un user au coté client
 * @throws : renvoie une exception pour indiquer que le mail a été utilisé par un autre user qui est deja inscrit
 * */
    public UserDTO registeruser(InscriptionDTO inscriptiondto)throws EmailDejaPrisException {
        User user = users.get(inscriptiondto.getMail());
        if (Objects.isNull(user)) {
            if(inscriptiondto.getRole().equals("admin")){
                user = new Admin(inscriptiondto.getUsername(),inscriptiondto.getMail(), inscriptiondto.getPassword());
            }else{
                user = new ClassicalUser(inscriptiondto.getUsername(),inscriptiondto.getMail(), inscriptiondto.getPassword());
            }

            users.put(inscriptiondto.getMail(), user);
            return User.toUserDTO(user);
        } else {
            throw new EmailDejaPrisException();
        }
    }

/**
 * */

    public PlaylisDTO registerPlaylist(PlaylisDTO playlisDTO)
            throws UserNotFoundException, EmailDejaPrisException, DonneesIncorrectException {

        if (playlisDTO.getName() == null
                || playlisDTO.getUsername() == null
                || playlisDTO.getDescription() == null
                || playlisDTO.getUserid() == null) {
            throw new DonneesIncorrectException();
        }

        // Chercher l’utilisateur par son ID
        User user = getUserbyid(playlisDTO.getUserid());
        if (user == null) {
            throw new UserNotFoundException();
        }

        // Vérification optionnelle : l’username correspond bien
        if (!user.getUsername().equals(playlisDTO.getUsername())) {
            throw new DonneesIncorrectException(); // incohérence entre id et username
        }

        Playlist playlist = new Playlist(
                playlisDTO.getName(),
                playlisDTO.getDescription(),
                user
        );

        playlists.put(playlisDTO.getName(), playlist);

        return playlist.toDTO();
    }





public UserDTO getUser(String mail,String password) throws DonneesIncorrectException {
    User user = users.get(mail);
    if (user != null && password.equals(user.getPassword()))
    {
        return User.toUserDTO(user);
    }else{
        throw new DonneesIncorrectException();
    }
}



public User getUserbyid(String id){

          for(User user : users.values()){
              if (user.getUserid().equals(id)){
                  return user;
              }
          }
          return null;
}

public PlaylisDTO getPlaylist(PlaylisDTO playlisDTO){
        User contructor = getUserbyid(playlisDTO.getUserid());
        if(contructor != null){
            return new PlaylisDTO(playlisDTO.getName(),playlisDTO.getDescription(),)
        }
}




}
