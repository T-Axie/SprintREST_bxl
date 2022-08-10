package be.bstorm.akimts.rest.bxl.service;

import be.bstorm.akimts.rest.bxl.model.dto.ReservationDTO;
import be.bstorm.akimts.rest.bxl.model.entities.Reservation;
import be.bstorm.akimts.rest.bxl.model.forms.ReservationCancellationForm;
import be.bstorm.akimts.rest.bxl.model.forms.ReservationRequestForm;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {

    ReservationDTO requestReservation(ReservationRequestForm form);
    boolean dateAvailable(LocalDateTime arrive, LocalDateTime depart);
    void cancelReservation(ReservationCancellationForm form);
    List<ReservationDTO> futureReservOfChild(Long childId);
    List<ReservationDTO> futureOfCurrentMonth();
}
