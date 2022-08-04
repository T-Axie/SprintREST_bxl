package be.bstorm.akimts.rest.bxl.controller;

import be.bstorm.akimts.rest.bxl.exceptions.ElementNotFoundException;
import be.bstorm.akimts.rest.bxl.mapper.TuteurMapper;
import be.bstorm.akimts.rest.bxl.model.dto.ErrorDTO;
import be.bstorm.akimts.rest.bxl.model.dto.TuteurDTO;
import be.bstorm.akimts.rest.bxl.model.entities.Tuteur;
import be.bstorm.akimts.rest.bxl.model.forms.TuteurForm;
import be.bstorm.akimts.rest.bxl.service.TuteurService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tuteur")
public class TuteurController {

    private final TuteurService service;
    private final TuteurMapper mapper;

    public TuteurController(TuteurService service, TuteurMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    // GET http://localhost:8080/tuteur/{id}
    @GetMapping("/{id:[0-9]+}")
    public TuteurDTO getOne(@PathVariable long id){
        return mapper.toDto( service.getOne(id) );
    }

    @GetMapping({"", "/all"})
    public List<TuteurDTO> getAll(){
        return service.getAll().stream()
                .map( mapper::toDto )
                .toList();
    }

    @PostMapping
    public TuteurDTO insert(@RequestBody TuteurForm form){
        return mapper.toDto( service.create( mapper.toEntity(form) ) );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable long id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public TuteurDTO update(@PathVariable long id, @RequestBody TuteurForm form ){

        Tuteur entity = mapper.toEntity(form);
        return mapper.toDto( service.update( id, entity ) );

    }



}
