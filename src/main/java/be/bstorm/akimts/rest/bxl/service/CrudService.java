package be.bstorm.akimts.rest.bxl.service;

import java.util.List;

public interface CrudService<T, TID> {

    // CREATE
    T create(T toInsert);

    // UPDATE
    T update(TID id, T toUpdate);

    // READ
    T getOne(TID id);
    List<T> getAll();

    // DELETE
    T delete(TID id);
    
}
