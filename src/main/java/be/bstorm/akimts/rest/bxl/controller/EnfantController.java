package be.bstorm.akimts.rest.bxl.controller;

import be.bstorm.akimts.rest.bxl.model.dto.EnfantDTO;
import be.bstorm.akimts.rest.bxl.model.dto.ReservationDTO;
import be.bstorm.akimts.rest.bxl.model.forms.EnfantInsertForm;
import be.bstorm.akimts.rest.bxl.model.forms.EnfantUpdateForm;
import be.bstorm.akimts.rest.bxl.service.EnfantService;
import be.bstorm.akimts.rest.bxl.service.ReservationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/enfant")
@CrossOrigin("http://localhost:4200/")
public class EnfantController {

    private final EnfantService service;
    private final ReservationService reservationService;

    public EnfantController(EnfantService service, ReservationService reservationService) {
        this.service = service;
        this.reservationService = reservationService;
    }

    @GetMapping("/{id:[0-9]+}")
    public EnfantDTO getOne(@PathVariable long id){
        return service.getOne(id);
    }

    @GetMapping({"", "/all"})
    public List<EnfantDTO> getAll(){
        return service.getAll();
    }

    @PostMapping
    public EnfantDTO insert(@Valid @RequestBody EnfantInsertForm form){
        return service.create(form);
    }

    @DeleteMapping("/{id:[0-9]+}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public EnfantDTO delete(@PathVariable long id){
        return service.delete(id);
    }

    @PutMapping("/{id:[0-9]+}")
    public EnfantDTO update(@PathVariable long id, @Valid @RequestBody EnfantUpdateForm form ){
        return service.update(id, form);
    }

    @PatchMapping("/{id:[0-9]+}")
    public EnfantDTO patchTuteurs(@PathVariable long id, @Valid @RequestBody Collection<Long> tuteurIds){
        return service.changeTuteurs(id, tuteurIds);
    }

    @GetMapping(value = "/allergie")
    public List<EnfantDTO> getAllWithAllergie(@RequestParam String allergie){
        return service.getAllWithAllergie(allergie);
    }

    // GET http://localhost:8080/enfant/{id}/reservation/future
    @GetMapping("/{id:[0-9]+}/reservation/future")
    public List<ReservationDTO> futureReservation(@PathVariable Long id){
        return reservationService.futureReservOfChild(id);
    }

    @GetMapping(value = "/on-day", params = "date")// 30-12-2020 : pattern= "dd-MM-yyyy"
    public List<EnfantDTO> presentOnDay(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date){
        return service.getAllPresentOnDay(date);
    }

}
