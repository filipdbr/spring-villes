package fr.diginamic.villes.web;

import fr.diginamic.villes.dto.DepartementDto;
import fr.diginamic.villes.exception.InvalidDepartementException;
import fr.diginamic.villes.mapper.DepartementMapper;
import fr.diginamic.villes.model.Departement;
import fr.diginamic.villes.model.Ville;
import fr.diginamic.villes.repository.DepartementRepository;
import fr.diginamic.villes.repository.VilleRepository;
import fr.diginamic.villes.service.DepartementService;
import fr.diginamic.villes.service.VilleService;
import org.apache.coyote.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createDepartement(@RequestBody DepartementDto departementDto) throws InvalidDepartementException {

        // Validate the department data
        departementService.validateDepartement(departementDto);

        // Map DTO to entity
        Departement departement = DepartementMapper.toBean(departementDto);

        // Return a success response with CREATED status and a message body
        departementRepository.save(departement);

        // Return a success response with CREATED status and a message body
        return ResponseEntity.status(HttpStatus.CREATED).body("Département créé avec succès");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDepartement(@PathVariable int id, @RequestBody DepartementDto departementDto) throws InvalidDepartementException {

        // Validate the department data
        departementService.validateDepartement(departementDto);

        // Find the existing department by its ID
        Departement departement = departementRepository.findById(id).orElseThrow(() -> new InvalidDepartementException("Le département n'existe pas."));

        // Update department name
        departement.setNomDepartement(departementDto.getNomDepartement());

        // Clear the current list of cities (if this is the behavior you want)
        departement.getVilles().clear();

        // Save the updated department along with its cities
        departementRepository.save(departement);

        // Return success message
        return ResponseEntity.status(HttpStatus.OK).body("Département mis à jour avec succès.");
    }


    // Deletes a departement (DELETE)
    @DeleteMapping
    public void deleteDepartement(@RequestParam int id) {
        departementRepository.deleteById(id);
    }

    // get n largest cities in the department
    @GetMapping("/{codeRegion}/villes/top/{n}")
    public List<Ville> getTopNCities(@PathVariable String codeRegion, @PathVariable int n) {
        return villeRepository.findByDepartement_CodeDepartementOrderByNbHabitantsDesc(codeRegion, Pageable.ofSize(n));
    }

    @GetMapping("/{codeRegion}/villes")
    public Iterable<Ville> getCitiesBetween(@PathVariable String codeRegion, @RequestParam int min, @RequestParam int max) {
        return villeRepository.findByDepartement_CodeDepartementAndNbHabitantsBetween(codeRegion, min, max);
    }

}
