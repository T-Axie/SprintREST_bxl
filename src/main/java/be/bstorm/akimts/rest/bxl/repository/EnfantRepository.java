package be.bstorm.akimts.rest.bxl.repository;

import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnfantRepository extends JpaRepository<Enfant, Long> {
}
