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
    public Enfant save(Enfant enfant) {
        if(enfant == null)
            throw new IllegalArgumentException("enfant ne devrait pas être null");

        return repository.save(enfant);
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
        return enfant;
    }
}
