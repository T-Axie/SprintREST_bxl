package be.bstorm.akimts.rest.bxl.model.forms;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class EnfantUpdateForm {

    @NotBlank
    @Size(max = 255)
    private String nom;
    @NotBlank
    @Size(max = 255)
    private String prenom;
    @PastOrPresent
    private LocalDate dateNaiss;
    private boolean propre = true;
    @NotNull
    @Size(max = 10)
    private List<String> allergies;
    @NotNull
    private Set<Long> tuteursId = new HashSet<>();

    // 2e manière d'envisager le mappage
    // A remarque que l'id et les tuteurs ne sont pas setté
//    public Enfant toEntity(){
//
//        Enfant entity = new Enfant();
//
//        entity.setPrenom(prenom);
//        entity.setNom(nom);
//        entity.setDateNaissance(dateNaiss);
//        entity.setPropre(propre);
//        entity.setAllergies(allergies);
//
//        return entity;
//
//    }

}
