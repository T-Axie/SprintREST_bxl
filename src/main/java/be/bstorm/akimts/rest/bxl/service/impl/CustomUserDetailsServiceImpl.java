package be.bstorm.akimts.rest.bxl.service.impl;

import be.bstorm.akimts.rest.bxl.model.entities.Utilisateur;
import be.bstorm.akimts.rest.bxl.model.forms.UtilisateurCreateForm;
import be.bstorm.akimts.rest.bxl.repository.UtilisateurRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UtilisateurRepository repository;
    private final PasswordEncoder encoder;

    public CustomUserDetailsServiceImpl(UtilisateurRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("connexion impossible"));
    }

    public void create(UtilisateurCreateForm toCreate){
        Utilisateur utilisateur = toCreate.toEntity();
        utilisateur.setPassword( encoder.encode(utilisateur.getPassword()) );
        repository.save( utilisateur );
    }

}
