package be.bstorm.akimts.rest.bxl.service.impl;

import be.bstorm.akimts.rest.bxl.exceptions.ElementNotFoundException;
import be.bstorm.akimts.rest.bxl.mapper.EnfantMapper;
import be.bstorm.akimts.rest.bxl.model.dto.EnfantDTO;
import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.model.entities.Tuteur;
import be.bstorm.akimts.rest.bxl.model.forms.EnfantInsertForm;
import be.bstorm.akimts.rest.bxl.model.forms.EnfantUpdateForm;
import be.bstorm.akimts.rest.bxl.repository.EnfantRepository;
import be.bstorm.akimts.rest.bxl.repository.TuteurRepository;
import be.bstorm.akimts.rest.bxl.service.EnfantService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
//@Primary
public class EnfantServiceImpl implements EnfantService {

    private final EnfantRepository repository;
    private final TuteurRepository tuteurRepository;
    private final EnfantMapper mapper;

    public EnfantServiceImpl(EnfantRepository repository, TuteurRepository tuteurRepository, EnfantMapper mapper) {
        this.repository = repository;
        this.tuteurRepository = tuteurRepository;
        this.mapper = mapper;
    }

    @Override
    public EnfantDTO create(EnfantInsertForm toInsert) {
        if( toInsert == null)
            throw new IllegalArgumentException("inserted child cannot be null");

        return mapper.toDto( repository.save( mapper.toEntity( toInsert ) ) );
    }

    @Override
    public EnfantDTO update(Long id, EnfantUpdateForm toUpdate) {
        if(toUpdate == null || id == null)
            throw new IllegalArgumentException("params cannot be null");

        if( !repository.existsById(id) )
            throw new ElementNotFoundException(Enfant.class, id);

        Enfant enfant = mapper.toEntity(toUpdate);
        List<Tuteur> tuteurs = tuteurRepository.findAllById(toUpdate.getTuteursId());
        enfant.setTuteurs( new HashSet<>(tuteurs) );

        return mapper.toDto( repository.save( enfant ) );
    }

    @Override
    public EnfantDTO getOne(Long id) {
        return repository.findById(id)
                .map( mapper::toDto )
                .orElseThrow(() -> new ElementNotFoundException(Enfant.class, id));
    }

    @Override
    public List<EnfantDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public EnfantDTO delete(Long id) {
        Enfant enfant = repository.findById(id)
                        .orElseThrow(() -> new ElementNotFoundException(Enfant.class, id));
        repository.delete(enfant);
        enfant.setId(null);
        return mapper.toDto(enfant);
    }

    @Override
    public EnfantDTO changeTuteurs(long id, Collection<Long> idTuteur) {

        Enfant enfant = repository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(Enfant.class, id));

        List<Tuteur> tuteurs = tuteurRepository.findAllById(idTuteur);

        // TODO check id tuteurs

        enfant.setTuteurs( new HashSet<>(tuteurs) );
        return mapper.toDto( repository.save(enfant) );
    }
}
