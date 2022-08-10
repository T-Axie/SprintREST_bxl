package be.bstorm.akimts.rest.bxl.repository;

import be.bstorm.akimts.rest.bxl.model.entities.Adresse;

import java.util.Optional;

public interface CustomAdresseRepository {

    Optional<Adresse> exists(Adresse adresse);

}
