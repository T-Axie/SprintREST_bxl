package be.bstorm.akimts.rest.bxl.controller;

import be.bstorm.akimts.rest.bxl.mapper.EnfantMapper;
import be.bstorm.akimts.rest.bxl.model.dto.EnfantDTO;
import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.model.forms.EnfantInsertForm;
import be.bstorm.akimts.rest.bxl.model.forms.EnfantUpdateForm;
import be.bstorm.akimts.rest.bxl.service.EnfantService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/enfant")
public class EnfantController {

    private final EnfantService service;
    private final EnfantMapper mapper;

    public EnfantController(EnfantService service, EnfantMapper mapper) {
        this.service = service;
        this.mapper = mapper;
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
    public EnfantDTO delete(@PathVariable long id){
        return mapper.toDto( service.delete(id) );
    }

    @PutMapping("/{id}")
    public EnfantDTO update(@PathVariable long id, @RequestBody EnfantUpdateForm form ){

        Enfant entity = mapper.toEntity(form);
        return mapper.toDto( service.update( id, entity ) );

    }


}
