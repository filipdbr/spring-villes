package fr.diginamic.villes.webservice;

import fr.diginamic.villes.entities.Ville;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
        return new ResponseEntity<>("Ville insérée avec succès", HttpStatus.OK);
    }

}
