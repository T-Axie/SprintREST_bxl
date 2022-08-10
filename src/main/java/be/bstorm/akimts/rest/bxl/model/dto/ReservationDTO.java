package be.bstorm.akimts.rest.bxl.model.dto;

import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.model.entities.Tuteur;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReservationDTO {

    private Long id;

    private LocalDateTime arrive;
    private LocalDateTime depart;

    private EnfantDTO enfant;

    private TuteurDTO tuteurDepose;
    private TuteurDTO tuteurReprend;

    @Data
    @Builder
    public static class EnfantDTO{
        private Long id;
        private String nom;
        private String prenom;
        private boolean propre;

        public static EnfantDTO toDto(Enfant entity){
            if(entity == null)
                return null;

            return EnfantDTO.builder()
                    .id(entity.getId() )
                    .nom(entity.getNom())
                    .prenom(entity.getPrenom())
                    .propre(entity.isPropre())
                    .build();
        }
    }

    @Data
    @Builder
    public static class TuteurDTO{
        private Long id;
        private String nom;
        private String prenom;
        private String numTel;

        public static TuteurDTO toDto(Tuteur entity){
            if(entity == null)
                return null;

            return TuteurDTO.builder()
                    .id(entity.getId())
                    .prenom(entity.getPrenom())
                    .nom(entity.getNom())
                    .numTel(entity.getNumTel())
                    .build();
        }
    }

}
