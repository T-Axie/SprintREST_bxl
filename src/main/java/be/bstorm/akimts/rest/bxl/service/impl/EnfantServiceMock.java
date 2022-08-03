package be.bstorm.akimts.rest.bxl.service.impl;

import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.service.EnfantService;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
// Mockito peut permettre de ne pas avoir à implementer de Mock
// par soit même
public class EnfantServiceMock implements EnfantService {
    @Override
    public Enfant create(Enfant toInsert) {
        return null;
    }

    @Override
    public Enfant update(Long id, Enfant toUpdate) {
        return null;
    }

    @Override
    public Enfant getOne(Long id) {
        return new Enfant();
    }

    @Override
    public List<Enfant> getAll() {
        return List.of(new Enfant());
    }

    @Override
    public void delete(Long id) {
    }
}
