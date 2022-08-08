package be.bstorm.akimts.rest.bxl.model.forms;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class TuteurForm {

    @NotBlank
    @Size(min = 2,max = 255)
    private String nom;
    @NotBlank
    @Size(min = 2,max = 255)
    private String prenom;
    @Valid
    @NotNull
    private AdresseForm adresse;
    @NotNull
    @Pattern(regexp = "^(((\\+|00)32[ ]?(?:\\(0\\)[ ]?)?)|0){1}(4(60|[789]\\d)\\/?(\\s?\\d{2}\\.?){2}(\\s?\\d{2})|(\\d\\/?\\s?\\d{3}|\\d{2}\\/?\\s?\\d{2})(\\.?\\s?\\d{2}){2})$")
    private String numTel;

}
