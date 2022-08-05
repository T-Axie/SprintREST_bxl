package be.bstorm.akimts.rest.bxl.model.forms;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class EnfantInsertForm {

    @NotBlank
    @Size(max = 255)
    private String nom;
    @NotBlank
    @Size(max = 255)
    private String prenom;
    @PastOrPresent// (message = "autre message")
    private LocalDate dateNaiss;
    private boolean propre = true;

}
