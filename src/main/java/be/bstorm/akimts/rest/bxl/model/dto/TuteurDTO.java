package be.bstorm.akimts.rest.bxl.model.dto;

import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class TuteurDTO {
    private Long id;
    private String prenom;
    private String nom;
    private String adresse;
    private String numTel;
    private Set<EnfantDTO> enfants;

    @Data
    @Builder
    public static class EnfantDTO {
        private Long id;
        private String nom;
        private String prenom;
        private LocalDate dateNaiss;
        private String proprete;
        private List<String> allergies;


        public static EnfantDTO fromEntity(Enfant entity){
            if( entity == null )
                return null;

            return EnfantDTO.builder()
                    .id( entity.getId() )
                    .prenom( entity.getPrenom() )
                    .nom( entity.getNom() )
                    .dateNaiss( entity.getDateNaissance() )
                    .allergies( entity.getAllergies() )
                    .proprete( entity.isPropre() ? "propre" : "non-propre" )
                    .build();
        }
    }
}
