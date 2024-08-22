package fr.diginamic.villes.services;

import fr.diginamic.villes.entities.Ville;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VilleService {

    // Injects an EntityManager, which handles database operations on entities.
    @PersistenceContext
    private EntityManager em;

    // Retrieves a list of all cities from the DB.
    @Transactional
    public List<Ville> extractVilles() {
        // Creates a JPQL query retrieving all cities from the table.
        return em.createQuery("select v from Ville v", Ville.class).getResultList();
    }

    // Retrieve a city by ID.
    @Transactional
    public Ville extractVille(int idVille) {
        if (em.find(Ville.class, idVille) != null) {
            Ville ville = em.find(Ville.class, idVille);
            return ville;
        }
        return null;
    }

    // Retrieve a city by name.
    @Transactional
    public Ville extractVille(String villeNom) {
        TypedQuery<Ville> query = em.createQuery("SELECT v FROM Ville v WHERE v.nom = :nom", Ville.class);
        query.setParameter("nom", villeNom);
        List<Ville> results = query.getResultList();
        // returns null if there's no city with such ID.
        return results.isEmpty() ? null : results.get(0);
    }

    // Insert a city into a DB.
    @Transactional
    public List<Ville> insertVille(Ville newVille) {
        em.persist(newVille);
        return extractVilles();
    }

    // Modifies a city (by ID)
    @Transactional
    public List<Ville> modifierVille(int idVille, Ville newVille) {
        Ville ville = em.find(Ville.class, idVille);
        if (ville == null) {
            // returns null if there's no city with such ID.
            return null;
        }
        ville.setNom(newVille.getNom());
        ville.setNbHabitants(newVille.getNbHabitants());
        em.merge(ville);
        return extractVilles();
    }

    // Deletes city by ID.
    @Transactional
    public List<Ville> supprimerVille(int idVille) {
        Ville ville = em.find(Ville.class, idVille);
        if (ville == null) {
            // returns null if there's no city with such ID.
            return null;
        }
        em.remove(ville);
        return extractVilles();
    }
}

