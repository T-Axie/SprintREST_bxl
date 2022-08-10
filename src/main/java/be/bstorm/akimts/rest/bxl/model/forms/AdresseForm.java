package be.bstorm.akimts.rest.bxl.model.forms;

import be.bstorm.akimts.rest.bxl.model.entities.Adresse;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class AdresseForm {

    @Positive
    private int numero;
//    @Min(97) @Max(122)
    private char boite;
    @NotBlank
    private String rue;
    @NotBlank
    private String ville;
    @Positive
    private int codePostal;

    public Adresse toEntity(){

        Adresse entity = new Adresse();

        entity.setVille(this.ville);
        entity.setRue(this.rue);
        entity.setBoite(this.boite);
        entity.setCodePostal(this.codePostal);
        entity.setNumero(this.numero);

        return entity;

    }

}
