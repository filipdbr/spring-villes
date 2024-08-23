package fr.diginamic.villes.webservice;

import fr.diginamic.villes.entities.Departement;
import fr.diginamic.villes.entities.Ville;
import fr.diginamic.villes.services.DepartementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departements")
public class DepartementContoleur {

    private final DepartementService departementService;

    // Constructor injection for DepartementService
    public DepartementContoleur(DepartementService departementService) {
        this.departementService = departementService;
    }

    // Get all departments
    @GetMapping
    public List<Departement> getDepartments() {
        return departementService.extractDepartements();
    }

    // Get top N cities in a department by population
    @GetMapping("/{id}/top/{n}")
    public List<Ville> departementTop(@PathVariable int id, @PathVariable int n) {
        return departementService.getTopNVilles(id, n);
    }

    // Get cities within a population range in a department
    @GetMapping("/region/{dep_id}/population")
    public List<Ville> departementPopulation(@PathVariable int dep_id, @RequestParam int min, @RequestParam int max) {
        return departementService.getVillesByPopulationRange(dep_id, min, max);
    }

    // Create a new department
    @PostMapping
    public List<Departement> createDepartement(@RequestBody Departement departement) {
        return departementService.insertDepartement(departement);
    }

    // Update an existing department by id
    @PutMapping("/{id}")
    public List<Departement> updateDepartement(@PathVariable int id, @RequestBody Departement departement) {
        return departementService.updateDepartement(id, departement);
    }

    // Delete a department by id
    @DeleteMapping("/{id}")
    public List<Departement> deleteDepartement(@PathVariable int id) {
        return departementService.deleteDepartement(id);
    }

}
