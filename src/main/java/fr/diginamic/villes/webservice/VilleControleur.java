package fr.diginamic.villes.webservice;

import fr.diginamic.villes.entities.Ville;
import fr.diginamic.villes.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Rest controller returns an object or a list of objects in form of a JSON
// This will work with a URL finishing with /villes
@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleService villeService;

    // GET method to retrieve the full list of cities.
    // This method is mapped to the "/villes" URL and returns a list of Ville objects
    @GetMapping
    public List<Ville> getVilles() {
        return villeService.extractVilles();
    }

    // GET method to retrieve a single city by its ID.
    // The ID is passed as a URL path variable (e.g., "/villes/1").
    @GetMapping("/{id}")
    public Ville getVille(@PathVariable Integer id) {
        return villeService.extractVille(id);
    }

    // GET method to retrieve a single city by its name.
    // The ID is passed as a URL path variable (e.g., "/villes/nom/lille").
    // additional nom in URL was necessary due to conflict between 2 getmappings
    @GetMapping("/nom/{nom}")
    public Ville getVilleNom(@PathVariable String nom) {
        return villeService.extractVille(nom);
    }

    // Creates a city in the DB
    @PostMapping
    public List<Ville> createVille(@RequestBody Ville ville) {
        return villeService.insertVille(ville);
    }

    // Updates a city in the DB
    @PutMapping("/{id}")
    public List<Ville> updateVille(@PathVariable Integer id, @RequestBody Ville ville) {
        return villeService.modifierVille(id, ville);
    }

    // Deletes the city from the DB
    @DeleteMapping("/{id}")
    public List<Ville> deleteVille(@PathVariable int id) {
        return villeService.supprimerVille(id);
    }

}
