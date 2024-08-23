package fr.diginamic.villes.services;

import fr.diginamic.villes.entities.Departement;
import fr.diginamic.villes.entities.Ville;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementService {

    @PersistenceContext
    private EntityManager em;

    // Retrieve all departments (R-READ)
    @Transactional
    public List<Departement> extractDepartements() {
        return em.createQuery("Select d from Departement d", Departement.class).getResultList();
    }

    // Insert a new department (C-CREATE)
    @Transactional
    public List<Departement> insertDepartement(Departement departement) {
        em.persist(departement);
        return extractDepartements();
    }

    // Update an existing department by id (U-UPDATE)
    @Transactional
    public List<Departement> updateDepartement(int id, Departement newDepartement) {
        Departement departement = em.find(Departement.class, id);
        if (departement != null) {
            departement.setNomDepartement(newDepartement.getNomDepartement());
            departement.setVilles(newDepartement.getVilles());
            em.merge(departement); // Merge the updated entity
        }
        return extractDepartements(); // Return the updated list
    }

    // Delete a department by id (D-DELETE)
    @Transactional
    public List<Departement> deleteDepartement(int id) {
        Departement departement = em.find(Departement.class, id);
        if (departement != null) {
            em.remove(departement);
        }
        return extractDepartements(); // Return the updated list after deletion
    }

    // Retrieve the top N cities in a department by population
    @Transactional
    public List<Ville> getTopNVilles(int departementId, int n) {
        return em.createQuery("SELECT v FROM Ville v WHERE v.departement.id = :departementId ORDER BY v.nbHabitants DESC", Ville.class)
                .setParameter("departementId", departementId)
                .setMaxResults(n)
                .getResultList();
    }

    // Retrieve cities in a department within a specified population range
    @Transactional
    public List<Ville> getVillesByPopulationRange(int departementId, int min, int max) {
        return em.createQuery("SELECT v FROM Ville v WHERE v.departement.id = :departementId AND v.nbHabitants BETWEEN :min AND :max", Ville.class)
                .setParameter("departementId", departementId)
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
    }
}
