package fr.diginamic.villes.service;

import fr.diginamic.villes.dto.VilleDto;
import fr.diginamic.villes.exception.InvalidVilleException;
import fr.diginamic.villes.model.Departement;
import fr.diginamic.villes.model.Ville;
import fr.diginamic.villes.repository.VilleRepository;
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

    // validation of creation of cities
    public void validateVille(Ville ville, Departement departement) throws InvalidVilleException {

        if (ville.getNbHabitants() < 10) {
            throw new InvalidVilleException("La ville doit avoir au moins 10 habitants");
        }

        if (ville.getNom() == null || ville.getNom().length() < 2) {
            throw new InvalidVilleException("La ville doit avoir un nom contenant au moins 2 lettres");
        }

        // in my case code_departement is an integer as it may be
        if (ville.getDepartement().getCodeDepartement().length() != 2) {
            throw new InvalidVilleException("Le code département doit obligatoire 2 caractères");
        }

        Ville existingVille = villeRepository.findByNom(ville.getNom());
        if (existingVille.getNom().equals(ville.getNom())) {
            throw new InvalidVilleException("Le nom de la ville doit être unique pour un département donné.");
        }
    }

}