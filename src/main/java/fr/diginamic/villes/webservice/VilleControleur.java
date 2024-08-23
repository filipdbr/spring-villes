package fr.diginamic.villes.webservice;

import fr.diginamic.villes.entities.Ville;
import fr.diginamic.villes.interfaces.VilleRepository;
import fr.diginamic.villes.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Rest controller returns an object or a list of objects in form of a JSON
// This will work with a URL finishing with /villes
@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleService villeService;
    @Autowired
    private VilleRepository villeRepository;

    // GET method to retrieve the full list of cities.
    // This method is mapped to the "/villes" URL and returns a list of Ville objects
    @GetMapping
    public Iterable<Ville> getVilles() {
        return villeRepository.findAll();
    }


    // GET method to retrieve a single city by its ID.
    // The ID is passed as a URL path variable (e.g., "/villes/1").
    @GetMapping("/{id}")
    public Ville getVille(@PathVariable Integer id) {
        return villeRepository.findById(id).get();
    }

    // GET method to retrieve a single city by its name.
    // The ID is passed as a URL path variable (e.g., "/villes/nom/lille").
    // additional nom in URL was necessary due to conflict between 2 getmappings
    @GetMapping("/search")
    public Ville getVilleNom(@RequestParam String nom) {
        return villeService.chercherNom(nom);
    }

    // Creates a city in the DB
    @PostMapping
    public Ville creerVille(@RequestBody Ville ville) {
        return villeRepository.save(ville);
    }

    // Updates a city in the DB
    @PutMapping
    public Ville updateVille(@RequestParam Integer id, @RequestBody Ville ville) {
        return villeService.updateVille(id, ville);
    }

    // Deletes the city from the DB
    @DeleteMapping
    public void deleteVille(@RequestParam int id) {
        villeRepository.deleteById(id);
    }

}
