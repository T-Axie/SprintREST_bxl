package be.bstorm.akimts.rest.bxl.mapper;

import be.bstorm.akimts.rest.bxl.model.dto.AdresseDTO;
import be.bstorm.akimts.rest.bxl.model.dto.TuteurDTO;
import be.bstorm.akimts.rest.bxl.model.entities.Tuteur;
import be.bstorm.akimts.rest.bxl.model.forms.TuteurForm;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TuteurMapper {

    public TuteurDTO toDto(Tuteur entity){
        if(entity == null)
            return null;

        Set<TuteurDTO.EnfantDTO> enfants = new HashSet<>();
        if( entity.getEnfants() != null )
            entity.getEnfants().stream()
                    .map( TuteurDTO.EnfantDTO::fromEntity )
                    .forEach(enfants::add);

        return TuteurDTO.builder()
                .id(entity.getId())
                .adresse(AdresseDTO.fromEntity(entity.getAdresse()))
                .prenom(entity.getPrenom())
                .nom(entity.getNom())
                .numTel(entity.getNumTel())
                .enfants( enfants )
                .build();
    }

    public Tuteur toEntity(TuteurForm form){
        if( form == null )
            return null;

        Tuteur entity = new Tuteur();
        entity.setPrenom(form.getPrenom());
        entity.setNom(form.getNom());
        entity.setAdresse(form.getAdresse().toEntity());
        entity.setNumTel(form.getNumTel());
        return entity;
    }

}
