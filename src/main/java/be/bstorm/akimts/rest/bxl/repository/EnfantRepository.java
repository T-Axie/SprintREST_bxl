package be.bstorm.akimts.rest.bxl.repository;

import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnfantRepository extends JpaRepository<Enfant, Long> {

    List<Enfant> findByAllergiesContaining(String allergie);

    List<Enfant> findByPrenomAndNom(String prenom, String nom);



}
