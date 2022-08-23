package be.bstorm.akimts.rest.bxl.controller;

import be.bstorm.akimts.rest.bxl.model.dto.TuteurDTO;
import be.bstorm.akimts.rest.bxl.model.forms.TuteurForm;
import be.bstorm.akimts.rest.bxl.service.TuteurService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tuteur")
@CrossOrigin("http://localhost:4200")
public class TuteurController {

    private final TuteurService service;

    public TuteurController(TuteurService service) {
        this.service = service;
    }

    // GET http://localhost:8080/tuteur/{id}
    @GetMapping("/{id:[0-9]+}")
    public TuteurDTO getOne(@PathVariable long id) {
        return service.getOne(id);
    }

    @GetMapping({ "", "/all" })
    public List<TuteurDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    public TuteurDTO insert(@Valid @RequestBody TuteurForm form) {
        return service.create(form);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TuteurDTO delete(@PathVariable long id) {
        return service.delete(id);
    }

    @PutMapping("/{id}")
    public TuteurDTO update(@PathVariable long id, @Valid @RequestBody TuteurForm form) {
        return service.update(id, form);
    }

    @GetMapping(params = "ville")
    public List<TuteurDTO> getAllFromVille(@RequestParam String ville) {
        return service.getAllFromVilleWithChild(ville);
    }

}
