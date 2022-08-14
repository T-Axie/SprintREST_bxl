package be.bstorm.akimts.rest.bxl.service.impl;

import be.bstorm.akimts.rest.bxl.exceptions.ElementNotFoundException;
import be.bstorm.akimts.rest.bxl.exceptions.ReferencedSuppresionException;
import be.bstorm.akimts.rest.bxl.mapper.TuteurMapper;
import be.bstorm.akimts.rest.bxl.model.dto.TuteurDTO;
import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.model.entities.Personne;
import be.bstorm.akimts.rest.bxl.model.entities.Tuteur;
import be.bstorm.akimts.rest.bxl.model.forms.TuteurForm;
import be.bstorm.akimts.rest.bxl.repository.AdresseRepository;
import be.bstorm.akimts.rest.bxl.repository.TuteurRepository;
import be.bstorm.akimts.rest.bxl.service.TuteurService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TuteurServiceImpl implements TuteurService {

    private final TuteurRepository repository;
    private final AdresseRepository adresseRepository;
    private final TuteurMapper mapper;

    public TuteurServiceImpl(TuteurRepository repository, AdresseRepository adresseRepository, TuteurMapper mapper) {
        this.repository = repository;
        this.adresseRepository = adresseRepository;
        this.mapper = mapper;
    }

    @Override
    public TuteurDTO create(TuteurForm toInsert) {
        Tuteur tuteur = mapper.toEntity(toInsert);

        // region alternative: via l'implementation custom
        adresseRepository.exists(tuteur.getAdresse()).ifPresentOrElse(
                tuteur::setAdresse,
                () -> tuteur.setAdresse(adresseRepository.save(tuteur.getAdresse()))
        );
        // endregion

//        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase();
//        adresseRepository.findAll( Example.of( tuteur.getAdresse(), matcher ) ).stream()
//                .findFirst()
//                .ifPresentOrElse(
//                        tuteur::setAdresse,
//                        () -> tuteur.setAdresse(adresseRepository.save(tuteur.getAdresse()))
//                );

        return mapper.toDto( repository.save( tuteur ) );
    }

    @Override
    public TuteurDTO update(Long id, TuteurForm toUpdate) {
        Tuteur tuteur = mapper.toEntity(toUpdate);
        tuteur.setId(id);
        return mapper.toDto( repository.save(tuteur) );
    }

    @Override
    public TuteurDTO getOne(Long id) {
        Tuteur tuteur = repository.findById(id)
                .orElseThrow( () -> new ElementNotFoundException(Tuteur.class, id));

        return mapper.toDto( tuteur );
    }

    @Override
    public List<TuteurDTO> getAll() {
        return repository.findAll().stream()
                .map( mapper::toDto )
                .toList();
    }

    @Override
    public TuteurDTO delete(Long id) {
        Tuteur tuteur = repository.findById(id)
                .orElseThrow( () -> new ElementNotFoundException(Tuteur.class, id));

        // Choix 1
//        if( tuteur.getEnfants() != null && !tuteur.getEnfants().isEmpty() )
//            throw new ReferencedSuppresionException(
//                    tuteur.getEnfants().stream()
//                            .map(Personne::getId)
//                            .collect(Collectors.toSet())
//            );

        // Choix 2
        try {
            repository.delete(tuteur);
        }
        catch (DataIntegrityViolationException ex){
            throw new ReferencedSuppresionException(
                    Enfant.class,
                    tuteur.getEnfants().stream()
                            .map(Personne::getId)
                            .collect(Collectors.toSet()),
                    ex
            );
        }
        tuteur.setId(null);
        return mapper.toDto( tuteur );
    }

    @Override
    public Set<TuteurDTO> getAllById(Collection<Long> ids) {
        return repository.findAllById(ids).stream()
                .map( mapper::toDto )
                .collect(Collectors.toSet());
    }

    @Override
    public List<TuteurDTO> getAllFromVilleWithChild(String ville) {



//        return repository.findByAdresse_VilleIsAndEnfantsNotEmpty(ville).stream()
        return repository.findFromVille(ville).stream()
                .map(mapper::toDto)
                .toList();
    }
}
