package be.bstorm.akimts.rest.bxl.repository;

import be.bstorm.akimts.rest.bxl.model.entities.Tuteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TuteurRepository extends JpaRepository<Tuteur, Long> {

    List<Tuteur> findByAdresse_VilleIsAndEnfantsNotEmpty(String ville);

    @Query("SELECT t FROM Tuteur t WHERE t.adresse.ville = ?1 AND (t.enfants.size > 0)")
    List<Tuteur> findFromVille(String ville);

}
