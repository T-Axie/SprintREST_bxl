package be.bstorm.akimts.rest.bxl.model.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@NoArgsConstructor
@Getter
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numero;
    private char boite;
    private String rue;
    private String ville;
    private int codePostal;

}
