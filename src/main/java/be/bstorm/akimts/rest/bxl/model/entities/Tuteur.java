package be.bstorm.akimts.rest.bxl.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Tuteur extends Personne {

    @Column(nullable = false)
    private String numTel;

    @ManyToOne
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;

    @ManyToMany(mappedBy = "tuteurs")
    private Set<Enfant> enfants;

}
