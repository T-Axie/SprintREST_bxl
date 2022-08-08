package be.bstorm.akimts.rest.bxl.service.impl;

import be.bstorm.akimts.rest.bxl.exceptions.ElementNotFoundException;
import be.bstorm.akimts.rest.bxl.exceptions.FormValidationException;
import be.bstorm.akimts.rest.bxl.exceptions.InvalidReferenceException;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
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

        MultiValueMap<String, String> validationErrors = null;

        if(toUpdate.getAllergies().stream()
                .anyMatch( (allergie) -> allergie == null || allergie.isBlank() || allergie.isEmpty())) {
            validationErrors = new LinkedMultiValueMap<>();
            validationErrors.add("allergies", "certaines allergies sont invalides");
        }

        Enfant enfant = mapper.toEntity(toUpdate);
        List<Tuteur> tuteurs = tuteurRepository.findAllById(toUpdate.getTuteursId());

        if( tuteurs.size() < toUpdate.getTuteursId().size() ){
            validationErrors = validationErrors == null ? new LinkedMultiValueMap<>() : validationErrors;
            validationErrors.add("tuteurs", "certains id ne menent pas à un tuteur");
        }

        if( validationErrors != null )
            throw new FormValidationException(validationErrors);

        enfant.setTuteurs( new HashSet<>(tuteurs) );
        enfant.setId(id);
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
    @Transactional
    public EnfantDTO delete(Long id) {
        Enfant enfant = repository.findById(id)
                        .orElseThrow(() -> new ElementNotFoundException(Enfant.class, id));
        repository.delete(enfant);
        enfant.setId(null);
        return mapper.toDto(enfant);
    }

    @Override
    public EnfantDTO changeTuteurs(long id, Collection<Long> idTuteurs) {

        Enfant enfant = repository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(Enfant.class, id));

        List<Tuteur> tuteurs = tuteurRepository.findAllById(idTuteurs);

        if( tuteurs.size() < idTuteurs.size() ){
            List<Long> found = tuteurs.stream()
                    .map(Tuteur::getId)
                    .toList();
            List<Long> notFound = idTuteurs.stream()
                    .filter( ident -> !found.contains(ident) )
                    .toList();

            throw new InvalidReferenceException(notFound);
        }

        enfant.setTuteurs( new HashSet<>(tuteurs) );
        return mapper.toDto( repository.save(enfant) );
    }

    @Override
    public List<EnfantDTO> getAllWithAllergie(String allergie) {
        return repository.findByAllergiesContaining(allergie).stream()
                .map(mapper::toDto)
                .toList();
    }


}
