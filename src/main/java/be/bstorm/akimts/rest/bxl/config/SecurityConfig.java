package be.bstorm.akimts.rest.bxl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // LES DROITS:

    // - permitAll : tous les visiteurs (connectés ou pas)
    // - denyAll : personne
    // - authenticated : connecté
    // - anonymous : pas connecté
    // - hasRole : possède le rôle particulier (un rôle est une autorité commencant par ROLE_)
    // - hasAnyRole : possède au moins un des rôles mentionnés
    // - hasAuthority : possède l'authorité particulier
    // - hasAnyAuthorities : possède au moins une des authorités mentionnés

    // - not(): methode avant un droit donnée pour un chemin pour obtenir l'opposé


    // ROLES POSSIBLES:

    // - ADMIN
    // - USER

    // AUTHORITES POSSIBLES:

    // - RECUPERER
    // - MODIFIER

    // LIAISONS:

    // - ADMIN: RECUPERER et MODIFIER et ROLE_ADMIN
    // - USER: RECUPERER et ROLE_USER


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.httpBasic();

        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // A ecrire du plus spécifique au plus général
        http.authorizeRequests()
                // region demo security
                .antMatchers("/security/test/????").authenticated()
                .antMatchers("/security/test/nobody").denyAll()
                .antMatchers("/security/test/connected").authenticated()
                .antMatchers("/security/test/not-connected").anonymous()
                .antMatchers("/security/test/role/user").hasRole("PERSONNEL")
                .antMatchers("/security/test/role/admin").hasRole("ADMIN")
                .antMatchers("/security/test/role/any").hasAnyRole("USER", "ADMIN")
                .antMatchers("/security/test/authority/READ").hasAuthority("ROLE_USER")
                .antMatchers("/security/test/authority/any").not().hasAnyAuthority("ROLE_USER", "WRITE")
                .antMatchers("/fake/request/{id::[0-9]+}/**").denyAll()
                // je peux utiliser:
                // - ? : joker pour de 0 à 1 caractère
                // - * : joker pour un segment de 0 à N caractères
                // - **: joker pour de 0 à N segments
                // - {pathVar:regex}: pattern regex pour un segment
                // endregion
                .antMatchers("/reserv/check").permitAll()
                .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                .antMatchers("/user/register").anonymous()
                .anyRequest().authenticated();

        return http.build();

    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder){
//        return new InMemoryUserDetailsManager(
//                List.of(
//                        User.builder()
//                                .username("user")
//                                .password(encoder.encode("pass"))
//                                .roles("PERSONNEL")
//                                .build(),
//                        User.builder()
//                                .username("admin")
//                                .password(encoder.encode("pass"))
//                                .roles("ADMIN")
//                                .build()
//                )
//        );
//    }

}
