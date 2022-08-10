package be.bstorm.akimts.rest.bxl.mapper;

import be.bstorm.akimts.rest.bxl.model.dto.ReservationDTO;
import be.bstorm.akimts.rest.bxl.model.entities.Reservation;
import be.bstorm.akimts.rest.bxl.model.forms.ReservationRequestForm;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper{

    public Reservation toEntity(ReservationRequestForm form){
        if(form == null)
            return null;

        Reservation reservation = new Reservation();

        reservation.setArrive(form.getArrive());
        reservation.setDepart(form.getDepart());

        return reservation;
    }

    public ReservationDTO toDto(Reservation entity){
        if( entity == null )
            return null;

        return ReservationDTO.builder()
                .id(entity.getId())
                .arrive(entity.getArrive())
                .depart(entity.getDepart())
                .enfant( ReservationDTO.EnfantDTO.toDto(entity.getEnfant()) )
                .tuteurDepose( ReservationDTO.TuteurDTO.toDto(entity.getTuteurDepose()) )
                .tuteurReprend( ReservationDTO.TuteurDTO.toDto(entity.getTuteurReprend()) )
                .build();
    }

}
