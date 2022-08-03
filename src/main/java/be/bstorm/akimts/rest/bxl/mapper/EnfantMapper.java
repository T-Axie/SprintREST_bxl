package be.bstorm.akimts.rest.bxl.mapper;

import be.bstorm.akimts.rest.bxl.model.dto.EnfantDTO;
import be.bstorm.akimts.rest.bxl.model.dto.TuteurDTO;
import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.model.forms.EnfantInsertForm;
import be.bstorm.akimts.rest.bxl.model.forms.EnfantUpdateForm;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EnfantMapper {

    private final TuteurMapper tuteurMapper;

    public EnfantMapper(TuteurMapper tuteurMapper) {
        this.tuteurMapper = tuteurMapper;
    }

    public EnfantDTO toDto(Enfant entity){

        if( entity == null )
            return null;

        Set<TuteurDTO> dtos = entity.getTuteurs().stream()
                .map( tuteurMapper::toDto )
                .collect(Collectors.toSet());

        return EnfantDTO.builder()
                .id( entity.getId() )
                .prenom( entity.getPrenom() )
                .nom( entity.getNom() )
                .dateNaiss( entity.getDateNaissance() )
                .allergies( entity.getAllergies() )
                .proprete( entity.isPropre() ? "propre" : "non-propre" ) // propre / non-propre
                .tuteurs( dtos )
                .build();

    }

    public Enfant toEntity(EnfantInsertForm form){

        if( form == null )
            return null;

        Enfant enfant = new Enfant();

        enfant.setPrenom( form.getPrenom() );
        enfant.setNom( form.getNom() );
        enfant.setDateNaissance( form.getDateNaiss() );
        enfant.setPropre( form.isPropre() );

        return enfant;

    }

    // Il est a remarquer qu'on ne mappe pas l'id ou les tuteurs
    public Enfant toEntity(EnfantUpdateForm form){

        if( form == null )
            throw new IllegalArgumentException();

        Enfant entity = new Enfant();

        entity.setPrenom(form.getPrenom());
        entity.setNom(form.getNom());
        entity.setDateNaissance(form.getDateNaiss());
        entity.setPropre(form.isPropre());
        entity.setAllergies(form.getAllergies());

        return entity;

    }

}
