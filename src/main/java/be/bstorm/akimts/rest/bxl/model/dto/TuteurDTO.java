package be.bstorm.akimts.rest.bxl.model.dto;

import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class TuteurDTO {
    private long id;
    private Set<EnfantDTO> enfants;
}
