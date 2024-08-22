package fr.diginamic.villes.webservice;

import fr.diginamic.villes.entities.Ville;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// Rest controller returns an object or a list of objects in form of a JSON
// This will work with a URL finishing with /villes
@RestController
@RequestMapping("/villes")
public class VilleControleur {

    // In-memory list to hold Ville (City) objects.
    private List<Ville> villes = new ArrayList<>();

    // Constructor that adds some sample cities (Ville) to the list.
    public VilleControleur() {
        villes.add(new Ville("Montpellier", 270000));
        villes.add(new Ville("Lille", 230000));
        villes.add(new Ville("Varsovie", 1700000));
    }

    // GET method to retrieve the full list of cities.
    // This method is mapped to the "/villes" URL and returns a list of Ville objects
    @GetMapping
    public List<Ville> getVilles() {
        return villes;
    }

    // GET method to retrieve a single city by its ID.
    // The ID is passed as a URL path variable (e.g., "/villes/1").
    @GetMapping("/{id}")
    public Ville getVilleById(@PathVariable Integer id) {
        // Loops searching the city with this id.
        for(Ville ville : villes) {
            if(ville.getId() == id) {
                return ville;
            }
        }
        // Return null if no city with the given ID is found.
        new ResponseEntity<>("Aucune ville avec un tel identifiant dans la base de données", HttpStatus.NOT_FOUND);
        return null;
    }

    // Creates a city in the DB
    @PostMapping
    public ResponseEntity<String> addVille(@RequestBody Ville newVille) {
        // Loop looking for a city with the same name.
        // As we use autoincrement, I can't compare the whole objects as they will always differ.
        for (Ville ville : villes) {
            if (ville.getNom().equals(newVille.getNom())) {
                // If there is a city with the same name, there following response is generated.
                return new ResponseEntity<>("La ville existe déjà", HttpStatus.BAD_REQUEST);
            }
        }
        // Add the city to the list of cities.
        villes.add(newVille);
        // Return obligatory response in order that the code compile
        return new ResponseEntity<>("La ville insérée avec succès", HttpStatus.OK);
    }

    // Updates a city in the DB
    @PutMapping("/{id}")
    public ResponseEntity<String> updateVille(@PathVariable Integer id, @RequestBody Ville newVille) {
        // Loop through the list of cities to find the city with the matching ID
        for (Ville ville : villes) {
            // If a city with the given ID is found
            if (ville.getId() == id) {
                // Update the city's name and number of inhabitants with the values from the newVille object
                ville.setNom(newVille.getNom());
                ville.setNbHabitatants(newVille.getNbHabitatants());
                // Return a success message with HTTP status 200 (OK)
                return new ResponseEntity<>("La ville a été mise à jour avec succès", HttpStatus.OK);
            }
        }
        // If no city with the given ID is found, return a 404 (Not Found) status with an error message
        return new ResponseEntity<>("Il n'y a pas de ville avec un tel identifiant dans la base de données", HttpStatus.NOT_FOUND);
    }

    // Deletes the city from the DB
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVille(@PathVariable Integer id) {
        // Loop through the list of cities to find the city with the matching ID
        for (Ville ville : villes) {
            // If a city with the given ID is found
            if (ville.getId() == id) {
                // City is removed and the proper response is shown
                villes.remove(ville);
                return new ResponseEntity<>(ville.getNom() + " a été supprimée", HttpStatus.OK);
            }
        }
        // Response if case of no match
        return new ResponseEntity<>("Il n'y a pas de ville avec un tel identifiant dans la base de données", HttpStatus.NOT_FOUND);
    }


}
