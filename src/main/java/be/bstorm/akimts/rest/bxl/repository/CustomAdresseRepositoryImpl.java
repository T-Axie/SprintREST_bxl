package be.bstorm.akimts.rest.bxl.repository;

import be.bstorm.akimts.rest.bxl.model.entities.Adresse;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class CustomAdresseRepositoryImpl implements CustomAdresseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Adresse> exists(Adresse adresse) {
        String request =
                    """
                    SELECT a
                    FROM Adresse a
                    WHERE
                      a.ville = ?1 AND
                      a.codePostal = ?2 AND
                      a.rue = ?3 AND
                      a.numero = ?4 AND
                      a.boite = ?5
                    """;

        TypedQuery<Adresse> query = entityManager.createQuery(request, Adresse.class);
        query.setParameter(1, adresse.getVille());
        query.setParameter(2, adresse.getCodePostal());
        query.setParameter(3, adresse.getRue());
        query.setParameter(4, adresse.getNumero());
        query.setParameter(5, adresse.getBoite());

        return query.getResultList().stream().findFirst();
    }
}
