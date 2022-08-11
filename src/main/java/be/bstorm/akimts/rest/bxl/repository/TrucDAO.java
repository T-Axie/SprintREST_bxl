package be.bstorm.akimts.rest.bxl.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TrucDAO {

    @PersistenceContext
    private EntityManager manager;

    public void machin(){
        // je manipule mes entity avec le manager
    }

}
