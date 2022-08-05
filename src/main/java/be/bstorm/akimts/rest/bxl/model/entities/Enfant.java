package be.bstorm.akimts.rest.bxl.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Enfant extends Personne {

    private LocalDate dateNaissance;
    private boolean propre;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> allergies;

    @ManyToMany
    @JoinTable(
            name = "tutorat",
            joinColumns = @JoinColumn(name = "enfant_id"),
            inverseJoinColumns = @JoinColumn(name = "tuteur_id")
    )
    private Set<Tuteur> tuteurs = new HashSet<>();

    public Enfant(String prenom, String nom, LocalDate dateNaissance, boolean propre) {
        super(prenom, nom);
        this.dateNaissance = dateNaissance;
        this.propre = propre;
    }

    public Enfant(String prenom, String nom, LocalDate dateNaissance, boolean propre, List<String> allergies) {
        super(prenom, nom);
        this.dateNaissance = dateNaissance;
        this.propre = propre;
        this.allergies = allergies;
    }


}
