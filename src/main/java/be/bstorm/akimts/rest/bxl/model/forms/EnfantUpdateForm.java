package be.bstorm.akimts.rest.bxl.model.forms;

import be.bstorm.akimts.rest.bxl.model.dto.TuteurDTO;
import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class EnfantUpdateForm {

    private String nom;
    private String prenom;
    private LocalDate dateNaiss;
    private boolean propre = true;
    private List<String> allergies;
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
