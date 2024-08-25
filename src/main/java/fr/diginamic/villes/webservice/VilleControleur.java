package fr.diginamic.villes.webservice;

import fr.diginamic.villes.model.Departement;
import fr.diginamic.villes.model.Ville;
import fr.diginamic.villes.interfaces.DepartementRepository;
import fr.diginamic.villes.interfaces.VilleRepository;
import fr.diginamic.villes.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Rest controller returns an object or a list of objects in form of a JSON
// This will work with a URL finishing with /villes
@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleService villeService;
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private DepartementRepository departementRepository;

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

    // find cities by the first letter of its name.
    @GetMapping("/search/by/letter")
    public Iterable<Ville> getVillesByLetter(@RequestParam char firstLetter) {
        return villeRepository.findByNomStartsWith(firstLetter);
    }

    // find cities by its population
    @GetMapping("/search/by/population")
    public Iterable<Ville> getVillesByPopulation(@RequestParam long minPopulation) {
        return villeRepository.findByNbHabitantsGreaterThan(minPopulation);
    }

    // find cities by its department and population
    @GetMapping("/search/by/departement")
    public Iterable<Ville> getVillesByDepartment(@RequestParam int codeDepartement, @RequestParam long population) {
        return villeRepository.findByDepartement_CodeDepartementAndNbHabitantsGreaterThan(codeDepartement, population);
    }

    // find cities with population between
    @GetMapping("/search/by/population/between")
    public Iterable<Ville> getVillesByPopulationBetween(@RequestParam Integer codeDepartement, @RequestParam long minPopulation, @RequestParam long maxPopulation) {
        return villeRepository.findByDepartement_CodeDepartementAndNbHabitantsBetween(codeDepartement, minPopulation, maxPopulation);
    }

    // find top n cities in the region in terms of population
    @GetMapping("/search/population/top")
    public List<Ville> getVillesTopPopulation(@RequestParam int codeDepartement, @RequestParam int n) {
        Pageable pageable = PageRequest.of(0, n); // Get the top 'n' results
        return villeRepository.findByDepartement_CodeDepartementOrderByNbHabitantsDesc(codeDepartement, pageable);
    }

    // Creates a city in the DB
    @PostMapping
    public ResponseEntity<?> createVille(@RequestBody Ville ville) {

        // Check if the department is provided
        Departement departement = ville.getDepartement();
        if (departement == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Department must be provided.");
        }

        // Check if the department has a name
        if (departement.getNomDepartement() == null || departement.getNomDepartement().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Department name must be provided.");
        }

        // Check if the department already exists in the database
        Departement existingDepartement = departementRepository.findByNomDepartement(departement.getNomDepartement());
        if (existingDepartement != null) {
            // If the department exists, associate the city with the existing department
            ville.setDepartement(existingDepartement);
        } else {
            // If the department doesn't exist, create it
            departementRepository.save(departement);
        }

        // Save the city with the associated department
        villeRepository.save(ville);

        return ResponseEntity.status(HttpStatus.CREATED).body(ville);
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
