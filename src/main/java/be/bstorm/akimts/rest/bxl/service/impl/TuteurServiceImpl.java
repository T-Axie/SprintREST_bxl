package be.bstorm.akimts.rest.bxl.service.impl;

import be.bstorm.akimts.rest.bxl.exceptions.ElementNotFoundException;
import be.bstorm.akimts.rest.bxl.model.entities.Tuteur;
import be.bstorm.akimts.rest.bxl.repository.TuteurRepository;
import be.bstorm.akimts.rest.bxl.service.TuteurService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TuteurServiceImpl implements TuteurService {

    private final TuteurRepository repository;

    public TuteurServiceImpl(TuteurRepository repository) {
        this.repository = repository;
    }

    @Override
    public Tuteur create(Tuteur toInsert) {
        if( toInsert == null )
            throw new IllegalArgumentException("parameter 0 cannot be null");

        toInsert.setId(null);
        return repository.save(toInsert);
    }

    @Override
    public Tuteur update(Long id, Tuteur toUpdate) {
        if( toUpdate == null || id == null )
            throw new IllegalArgumentException("parameters cannot be null");

        if( !repository.existsById(id) )
            throw new ElementNotFoundException(Tuteur.class, id);

        toUpdate.setId(id);
        return repository.save( toUpdate );
    }

    @Override
    public Tuteur getOne(Long id) {
        if( id == null )
            throw new IllegalArgumentException("id cannot be null");

        return repository.findById(id)
                .orElseThrow( () -> new ElementNotFoundException(Tuteur.class, id) );
    }

    @Override
    public List<Tuteur> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        if( id == null )
            throw new IllegalArgumentException("id cannot be null");

        if( !repository.existsById(id) )
            throw new ElementNotFoundException(Tuteur.class, id);

        repository.deleteById(id);
    }

    @Override
    public Set<Tuteur> getAllById(Collection<Long> ids) {
        return new HashSet<>( repository.findAllById(ids) );
    }
}
