package be.bstorm.akimts.rest.bxl.service;

import be.bstorm.akimts.rest.bxl.model.dto.TuteurDTO;
import be.bstorm.akimts.rest.bxl.model.forms.TuteurForm;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface TuteurService extends CrudService<TuteurDTO, Long, TuteurForm, TuteurForm> {

    Set<TuteurDTO> getAllById(Collection<Long> ids);

    List<TuteurDTO> getAllFromVilleWithChild(String ville);

}
