package fr.diginamic.villes.webservice;

import fr.diginamic.villes.model.Departement;
import fr.diginamic.villes.model.Ville;
import fr.diginamic.villes.interfaces.DepartementRepository;
import fr.diginamic.villes.interfaces.VilleRepository;
import fr.diginamic.villes.services.DepartementService;
import fr.diginamic.villes.services.VilleService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departements")
public class DepartementContoleur {

    private final DepartementService departementService;
    private final DepartementRepository departementRepository;
    private final VilleRepository villeRepository;

    // Constructor injection for DepartementService
    public DepartementContoleur(DepartementService departementService, DepartementRepository departementRepository, VilleService villeService, VilleRepository villeRepository) {
        this.departementService = departementService;
        this.departementRepository = departementRepository;
        this.villeRepository = villeRepository;
    }

    // Get all departments (READ)
    @GetMapping
    public Iterable<Departement> getDepartments() {
        return departementRepository.findAll();
    }

    // Create a department (CREATE)
    @PostMapping
    public void createDepartement(@RequestBody Departement departement) {
        departementRepository.save(departement);
    }

    // Updates a department (UPDATE)
    @PutMapping("/update")
    public void updateDepartement(@RequestParam int id, @RequestBody Departement departement) {
        departementService.updateDepartement(id, departement);
    }

    // Deletes a departement (DELETE)
    @DeleteMapping
    public void deleteDepartement(@RequestParam int id) {
        departementRepository.deleteById(id);
    }

    // get n largest cities in the department
    @GetMapping("/{codeRegion}/villes/top/{n}")
    public List<Ville> getTopNCities(@PathVariable int codeRegion, @PathVariable int n) {
        return villeRepository.findByDepartement_CodeDepartementOrderByNbHabitantsDesc(codeRegion, Pageable.ofSize(n));
    }

    @GetMapping("/{codeRegion}/villes")
    public Iterable<Ville> getCitiesBetween(@PathVariable int codeRegion, @RequestParam int min, @RequestParam int max) {
        return villeRepository.findByDepartement_CodeDepartementAndNbHabitantsBetween(codeRegion, min, max);
    }

}
