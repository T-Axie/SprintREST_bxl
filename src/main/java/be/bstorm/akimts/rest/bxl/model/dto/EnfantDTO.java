package be.bstorm.akimts.rest.bxl.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class EnfantDTO {

    private Long id;
    private String nom;
    private String prenom;
    private LocalDate dateNaiss;
    private String proprete;
    private List<String> allergies;
    private Set<TuteurDTO> tuteurs;

}
