package be.bstorm.akimts.rest.bxl.service;

import java.util.List;

public interface CrudService<T, TID> {

    // CREATE / UPDATE
    T save(T T);

    // READ
    T getOne(TID id);
    List<T> getAll();

    // DELETE
    T delete(TID id);
    
}
