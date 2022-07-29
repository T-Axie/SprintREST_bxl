package be.bstorm.akimts.rest.bxl.controller;

import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.model.entities.Personne;
import be.bstorm.akimts.rest.bxl.model.entities.Tuteur;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/demo")
public class DemoController {

    // GET - http://localhost:8080/personne
    @GetMapping("/personne")
    @ResponseBody
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public Personne getPerson(){
        return new Enfant("alex", "kim", LocalDate.now(), true);
    }

    // POST - http://localhost:8080/personne
    @PostMapping("/personne")
    public void writePerson(@RequestBody Personne personne, @RequestParam("repetition") int iter){

        for (int i = 0; i < iter; i++) {
            System.out.println(personne);
        }

    }

    // GET - http://localhost:8080/params
    @GetMapping("/params")
    public void writeParams(@RequestParam Map<String, Object> params){
        for (String key : params.keySet()) {
            System.out.println(key + " - " + params.get(key));
        }
    }

    // GET - http://localhost:8080/headers
    @GetMapping("/headers")
    public void writeArtificialHeader( @RequestHeader String artificial ){
        System.out.println(artificial);
    }

    // GET - http://localhost:8080/headers/all
    @GetMapping("/headers/all")
    public void writeAllHeaders( @RequestHeader HttpHeaders headers ){
        for (String key : headers.keySet()) {
            System.out.println( key + " - " + headers.get(key) );
        }
    }

    // GET - http://localhost:8080/demo/{id}
    @GetMapping("/demo/{id:[0-8]{1,3}}")
    public void demoPathVar( @PathVariable int id ){
        System.out.println("L'id selectionné est: " + id);
    }

    @GetMapping("/request")
    public void recupRequest(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request);
        System.out.println(response);
    }

    @GetMapping("/personne/new")
    public ResponseEntity<Personne> getPersonne(@RequestParam("name") String nom, @RequestParam("firstname") String prenom){
        Personne body = new Enfant(prenom, nom, LocalDate.now(), true);

        HttpHeaders headers = new HttpHeaders();
        headers.add("artificial", "ma valeur");

        HttpStatus status = HttpStatus.OK;

//        return new ResponseEntity<>(body, headers, status);
        return ResponseEntity.status(status)
                .header("arbitrary", "1e valeur", "2e valeur")
                .headers( headers )
                .body(body);
    }

}
