package fr.diginamic.villes.interfaces;

import fr.diginamic.villes.entities.Ville;
import org.springframework.data.repository.CrudRepository;

public interface VilleRepository  extends CrudRepository<Ville, Integer> {

    // find a city by its name. The methods in Interfaces are public by default.
    Ville findByNom(String nom);
}
