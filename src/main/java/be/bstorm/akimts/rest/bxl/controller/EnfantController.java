package be.bstorm.akimts.rest.bxl.controller;

import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.service.EnfantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnfantController {

    private final EnfantService service;

    public EnfantController(EnfantService service) {
        this.service = service;
    }

    @GetMapping("/enfant/{id:[0-9]+}")
    public Enfant getOne(@PathVariable long id){
        return service.getOne(id);
    }

    // GET ALL - INSERT
}
