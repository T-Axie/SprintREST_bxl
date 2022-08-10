package be.bstorm.akimts.rest.bxl.model.forms;

import be.bstorm.akimts.rest.bxl.validation.constraint.ReservationValid;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@ReservationValid
public class ReservationRequestForm {

    @NotNull
    @Future
    private LocalDateTime arrive;
    @NotNull
    @Future
    private LocalDateTime depart;
    @NotNull
    @Positive
    private Long enfantId;
    @NotNull
    @Positive
    private Long tuteurDepot;
    @NotNull
    @Positive
    private Long tuteurDepart;

}
