package be.bstorm.akimts.rest.bxl.service;

import be.bstorm.akimts.rest.bxl.model.entities.Tuteur;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface TuteurService extends CrudService<Tuteur, Long> {

    Set<Tuteur> getAllById(Collection<Long> ids);

}
