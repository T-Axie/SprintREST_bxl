package be.bstorm.akimts.rest.bxl.service.impl;

import be.bstorm.akimts.rest.bxl.exceptions.ElementNotFoundException;
import be.bstorm.akimts.rest.bxl.service.CrudService;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.EntityNotFoundException;
import java.util.List;

public abstract class AbstractCrudService<T, TID> implements CrudService<T, TID> {

    protected final JpaRepository<T,TID> repository;
    protected final Class<?> entityClass;

    protected AbstractCrudService(JpaRepository<T, TID> repository, Class<?> entityClass) {
        this.repository = repository;
        this.entityClass = entityClass;
    }

    @Override
    public T getOne(TID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(Entity.class, id));
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(TID id) {
        T entity = getOne(id);
        repository.delete(entity);
    }
}
