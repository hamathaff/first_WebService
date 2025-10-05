package fr.orleans.m1miage.wsi.tp2.controleur;

import fr.orleans.m1miage.wsi.tp2.dtos.InscriptionDTO;
import fr.orleans.m1miage.wsi.tp2.dtos.LoginDTO;
import fr.orleans.m1miage.wsi.tp2.dtos.PlaylisDTO;
import fr.orleans.m1miage.wsi.tp2.dtos.UserDTO;
import fr.orleans.m1miage.wsi.tp2.exceptions.DonneesIncorrectException;
import fr.orleans.m1miage.wsi.tp2.exceptions.EmailDejaPrisException;
import fr.orleans.m1miage.wsi.tp2.exceptions.UserNotFoundException;
import fr.orleans.m1miage.wsi.tp2.facade.FacadePlaylist;
import fr.orleans.m1miage.wsi.tp2.modele.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/videoplaystore")
public class Controleur {
    @Autowired
    private final FacadePlaylist facadePlaylist;
    private static final String BASE_URL = "/api/videoplaystore";
    public static final String URI_USER = BASE_URL + "/users/{id}";
    public static final String URI_PLAYLIST = BASE_URL + "/playlist";


    public Controleur(FacadePlaylist facadeUserVideo) {
        this.facadePlaylist = facadeUserVideo;
    }
    @PostMapping("/users")
    public ResponseEntity<UserDTO> inscription(@RequestBody InscriptionDTO inscriptionDTO, UriComponentsBuilder uriComponentsBuilder) {
        try{
            UserDTO userdto = this.facadePlaylist.registeruser(inscriptionDTO);
            URI uri = uriComponentsBuilder
                    .path(URI_USER)
                    .buildAndExpand(userdto.getUserid())
                    .toUri();
            return ResponseEntity.ok(userdto);
        }catch(EmailDejaPrisException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }




    @PostMapping("/playlist")
    public ResponseEntity<String> addplaylist(@RequestParam String login,@RequestParam String password,@RequestBody PlaylisDTO playlisDTO, UriComponentsBuilder uriComponentsBuilder) {
        try
        {
            UserDTO userDTO = facadePlaylist.getUser(login ,password);
            PlaylisDTO playlisDTO1 = this.facadePlaylist.registerPlaylist(playlisDTO);
            URI uri = uriComponentsBuilder
                    .path(URI_PLAYLIST)
                    .buildAndExpand(playlisDTO1.getName())
                    .toUri();
            return ResponseEntity.created(uri.create(uri.toString())).build();

        } catch (UserNotFoundException | DonneesIncorrectException | EmailDejaPrisException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUser(@RequestParam String login, @RequestParam String id, @RequestParam String password) {
        if (login == null || password == null || id == null) {
            throw new NullPointerException("id or mail or password null");
        }
        User user = facadePlaylist.getUserbyid(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        if (!user.getPassword().equals(password) || !user.getEmail().equals(login)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserDTO myUserDto = User.toUserDTO(user);
        return ResponseEntity.ok(myUserDto);
    }

}
