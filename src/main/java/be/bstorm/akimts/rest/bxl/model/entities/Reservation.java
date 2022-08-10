package be.bstorm.akimts.rest.bxl.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime arrive;
    @Column(nullable = false)
    private LocalDateTime depart;

    private boolean annule = false;
    private String motifAnnulation;

    @ManyToOne(optional = false)
    private Tuteur tuteurDepose;
    @ManyToOne(optional = false)
    private Tuteur tuteurReprend;
    @ManyToOne(optional = false)
    private Enfant enfant;

}
