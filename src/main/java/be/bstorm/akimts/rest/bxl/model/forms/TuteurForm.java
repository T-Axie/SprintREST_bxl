package be.bstorm.akimts.rest.bxl.model.forms;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class TuteurForm {

    @NotBlank
    private String nom;
    private String prenom;
    private String adresse;
    private String numTel;

}
