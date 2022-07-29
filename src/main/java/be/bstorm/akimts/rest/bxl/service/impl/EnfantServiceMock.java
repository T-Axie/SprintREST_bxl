package be.bstorm.akimts.rest.bxl.service.impl;

import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.service.EnfantService;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class EnfantServiceMock implements EnfantService {
    @Override
    public Enfant save(Enfant T) {
        return new Enfant();
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
    public Enfant delete(Long id) {
        if(id == 5)
            return null;

        return new Enfant();
    }
}
