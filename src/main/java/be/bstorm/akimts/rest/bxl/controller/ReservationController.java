package be.bstorm.akimts.rest.bxl.controller;

import be.bstorm.akimts.rest.bxl.model.dto.ReservationDTO;
import be.bstorm.akimts.rest.bxl.model.forms.ReservationCancellationForm;
import be.bstorm.akimts.rest.bxl.model.forms.ReservationRequestForm;
import be.bstorm.akimts.rest.bxl.service.ReservationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reserv")
public class ReservationController {

    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @PostMapping("/request")
    public ReservationDTO requestReserv(@Valid @RequestBody ReservationRequestForm form){
        return service.requestReservation(form);
    }

    @GetMapping(value = "/check", params = {"arrival", "departure"})
    public boolean checkAvailable( // 2020-10-10T00:00:00
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime arrival,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departure
    ){
        return service.dateAvailable(arrival, departure);
    }

    @PatchMapping("/cancel")
    public void cancelReservation(@Valid @RequestBody ReservationCancellationForm form){
        service.cancelReservation(form);
    }

    @GetMapping("/future/month")
    public List<ReservationDTO> getFutureReservOfCurrentMonth(){
        return service.futureOfCurrentMonth();
    }
}
