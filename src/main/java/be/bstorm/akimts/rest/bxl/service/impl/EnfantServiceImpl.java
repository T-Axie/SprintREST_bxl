package be.bstorm.akimts.rest.bxl.service.impl;

import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.repository.EnfantRepository;
import be.bstorm.akimts.rest.bxl.service.EnfantService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
//@Primary
public class EnfantServiceImpl implements EnfantService {

    private final EnfantRepository repository;

    public EnfantServiceImpl(EnfantRepository repository) {
        this.repository = repository;
    }

    @Override
    public Enfant create(Enfant toInsert) {
        if( toInsert == null)
            throw new IllegalArgumentException("inserted child cannot be null");

        toInsert.setId(null);

        return repository.save(toInsert);
    }

    @Override
    public Enfant update(Long id, Enfant toUpdate) {
        if(toUpdate == null || id == null)
            throw new IllegalArgumentException("params cannot be null");

        if( !repository.existsById(id) )
            throw new EntityNotFoundException();

        toUpdate.setId(id);

        // TODO gérer les tuteurs

        return repository.save(toUpdate);
    }

    @Override
    public Enfant getOne(Long id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Enfant> getAll() {
        return repository.findAll();
    }

    @Override
    public Enfant delete(Long id) {
        Enfant enfant = getOne(id);
        repository.delete(enfant);
        enfant.setId(0L);
        return enfant;
    }
}
