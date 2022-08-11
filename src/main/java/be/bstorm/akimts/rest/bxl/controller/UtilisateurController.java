package be.bstorm.akimts.rest.bxl.controller;

import be.bstorm.akimts.rest.bxl.model.forms.UtilisateurCreateForm;
import be.bstorm.akimts.rest.bxl.service.impl.CustomUserDetailsServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UtilisateurController {

    private final CustomUserDetailsServiceImpl service;

    public UtilisateurController(CustomUserDetailsServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/register")
    public void createUser(@Valid @RequestBody UtilisateurCreateForm form){
        service.create(form);
    }

}
