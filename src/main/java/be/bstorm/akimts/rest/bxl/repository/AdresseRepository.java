package be.bstorm.akimts.rest.bxl.repository;

import be.bstorm.akimts.rest.bxl.model.entities.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdresseRepository extends JpaRepository<Adresse, Long>, CustomAdresseRepository {
}
