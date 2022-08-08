package be.bstorm.akimts.rest.bxl.model.dto;

import be.bstorm.akimts.rest.bxl.model.entities.Adresse;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@Builder
public class AdresseDTO {

    private Long numero;
    private char boite;
    private String rue;
    private String ville;
    private int codePostal;

    public static AdresseDTO fromEntity(Adresse entity){
        if(entity == null)
            return null;

        return AdresseDTO.builder()
                .numero(entity.getId())
                .ville(entity.getVille())
                .rue(entity.getRue())
                .boite(entity.getBoite())
                .codePostal(entity.getCodePostal())
                .build();
    }

}
