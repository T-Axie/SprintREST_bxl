package be.bstorm.akimts.rest.bxl.controller;

import be.bstorm.akimts.rest.bxl.exceptions.ElementNotFoundException;
import be.bstorm.akimts.rest.bxl.mapper.EnfantMapper;
import be.bstorm.akimts.rest.bxl.model.dto.EnfantDTO;
import be.bstorm.akimts.rest.bxl.model.dto.ErrorDTO;
import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.model.entities.Tuteur;
import be.bstorm.akimts.rest.bxl.model.forms.EnfantInsertForm;
import be.bstorm.akimts.rest.bxl.model.forms.EnfantUpdateForm;
import be.bstorm.akimts.rest.bxl.service.EnfantService;
import be.bstorm.akimts.rest.bxl.service.TuteurService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/enfant")
public class EnfantController {

    private final EnfantService service;
    private final EnfantMapper mapper;
    private final TuteurService tuteurService;

    public EnfantController(EnfantService service, EnfantMapper mapper, TuteurService tuteurService) {
        this.service = service;
        this.mapper = mapper;
        this.tuteurService = tuteurService;
    }

    @GetMapping("/{id:[0-9]+}")
    public EnfantDTO getOne(@PathVariable long id){
        return mapper.toDto( service.getOne(id) );
    }

    @GetMapping({"", "/all"})
    public List<EnfantDTO> getAll(){
        return service.getAll().stream()
                .map( mapper::toDto )
                .toList();
    }

    @PostMapping
    public EnfantDTO insert(@RequestBody EnfantInsertForm form){
        Enfant entity = mapper.toEntity(form);
        entity = service.create( entity );
        return mapper.toDto( entity );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable long id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public EnfantDTO update(@PathVariable long id, @RequestBody EnfantUpdateForm form ){

        Enfant entity = mapper.toEntity(form);

        Set<Tuteur> tuteurs = null;
        if( form.getTuteursId() != null && !form.getTuteursId().isEmpty() )
            tuteurs = tuteurService.getAllById( form.getTuteursId() );

        entity.setTuteurs( tuteurs );
        return mapper.toDto( service.update( id, entity ) );

    }


}
