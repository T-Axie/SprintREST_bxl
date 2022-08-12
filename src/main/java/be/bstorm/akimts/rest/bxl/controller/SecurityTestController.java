package be.bstorm.akimts.rest.bxl.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/security/test")
public class SecurityTestController {

    @PreAuthorize("permitAll()") // se fait avant la méthode
    @PostAuthorize("returnObject.startsWith('o')") // se fait après la méthode
    @GetMapping("/all")
    public String allAccess(){
        return "ok";
    }

    @GetMapping("/nobody")
    public String nobody(){
        return "ok";
    }

    @GetMapping("/connected")
    public String connected(){
        return "ok";
    }

    @GetMapping("/not-connected")
    public String notConnected(){
        return "ok";
    }

    @Secured("ROLE_USER") // seulement pour les authorities
    @GetMapping("/role/user")
    public String roleUser(){
        return "ok";
    }

    @GetMapping("/role/admin")
    public String roleAdmin(){
        return "ok";
    }

//    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/role/any")
    public String isAdminOrUser(){
        return "ok";
    }

    @GetMapping("/authority/READ")
    public String hasReadAuthority(){
        return "ok";
    }

    @GetMapping("/authority/any")
    public String hasReadOrWriteAuthority(){
        return "ok";
    }

}
