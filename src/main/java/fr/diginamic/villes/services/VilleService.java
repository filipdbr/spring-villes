package fr.diginamic.villes.services;

import fr.diginamic.villes.entities.Ville;
import fr.diginamic.villes.interfaces.VilleRepository;
import org.springframework.stereotype.Service;

@Service
public class VilleService {

    private final VilleRepository villeRepository;

    public VilleService(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

    // find city by it's name  (READ)
    public Ville chercherNom(String nom) {
        return villeRepository.findByNom(nom);
    }

    // update a city to a DB (UPDATE)
    public Ville updateVille(int id, Ville newVille) {
        Ville ville = villeRepository.findById(id).orElse(null);
        if (ville == null) {
            return null;
        }
        ville.setNom(newVille.getNom());
        ville.setNbHabitants(newVille.getNbHabitants());
        return villeRepository.save(ville);
    }

}