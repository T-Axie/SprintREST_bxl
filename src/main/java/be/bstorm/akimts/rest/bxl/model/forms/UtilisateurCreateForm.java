package be.bstorm.akimts.rest.bxl.model.forms;

import be.bstorm.akimts.rest.bxl.model.entities.Utilisateur;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class UtilisateurCreateForm {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public Utilisateur toEntity(){

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setUsername(username);
        utilisateur.setPassword(password);
        utilisateur.setRoles(List.of("PERSONNEL"));
        return utilisateur;

    }

}
