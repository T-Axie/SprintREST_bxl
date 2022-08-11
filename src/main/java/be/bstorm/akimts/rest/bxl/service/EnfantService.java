package be.bstorm.akimts.rest.bxl.service;

import be.bstorm.akimts.rest.bxl.model.dto.EnfantDTO;
import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.model.forms.EnfantInsertForm;
import be.bstorm.akimts.rest.bxl.model.forms.EnfantUpdateForm;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface EnfantService extends CrudService<EnfantDTO, Long, EnfantInsertForm, EnfantUpdateForm> {

    EnfantDTO changeTuteurs(long id, Collection<Long> idTuteur);

    List<EnfantDTO> getAllWithAllergie(String allergie);

    List<EnfantDTO> getAllPresentOnDay(LocalDate date);

}
