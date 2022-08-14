package be.bstorm.akimts.rest.bxl.repository;

import be.bstorm.akimts.rest.bxl.model.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // Ne gère le cas: 1 reserv par heure sans conflit + 1 reserv de 10h
    @Query("SELECT (COUNT(r) < 10) FROM Reservation r WHERE NOT(r.depart < ?1 OR r.arrive > ?2) AND r.annule = false")
    boolean checkAvailable(LocalDateTime arrive, LocalDateTime depart);

    @Modifying
    @Transactional
    @Query("UPDATE Reservation r SET r.annule = true, r.motifAnnulation = ?2 WHERE r.id = ?1 AND r.annule = false")
    void cancelReservation(Long id, String motif);

    @Query("SELECT r FROM Reservation r WHERE r.enfant.id = ?1 AND r.arrive > current_date AND r.annule = false")
    List<Reservation> futurReservOfChild(Long id);

    // Alternative 1
    List<Reservation> findByArriveAfterAndDepartBeforeAndAnnuleFalse(LocalDateTime begin, LocalDateTime end);
    // Alternative 2
    @Query("SELECT r FROM Reservation r WHERE MONTH(CURRENT_DATE) = MONTH(r.arrive) AND r.arrive >= current_date AND r.annule = false")
    List<Reservation> findForCurrentMonth();

}
