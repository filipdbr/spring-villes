package fr.diginamic.villes.web;

import fr.diginamic.villes.dto.DepartementDto;
import fr.diginamic.villes.dto.VilleDto;
import fr.diginamic.villes.exception.InvalidVilleException;
import fr.diginamic.villes.mapper.DepartementMapper;
import fr.diginamic.villes.mapper.VilleMapper;
import fr.diginamic.villes.model.Departement;
import fr.diginamic.villes.model.Ville;
import fr.diginamic.villes.repository.DepartementRepository;
import fr.diginamic.villes.repository.VilleRepository;
import fr.diginamic.villes.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public Iterable<Ville> getVillesByDepartment(@RequestParam String codeDepartement, @RequestParam long population) {
        return villeRepository.findByDepartement_CodeDepartementAndNbHabitantsGreaterThan(codeDepartement, population);
    }

    // find cities with population between
    @GetMapping("/search/by/population/between")
    public Iterable<Ville> getVillesByPopulationBetween(@RequestParam String codeDepartement, @RequestParam long minPopulation, @RequestParam long maxPopulation) {
        return villeRepository.findByDepartement_CodeDepartementAndNbHabitantsBetween(codeDepartement, minPopulation, maxPopulation);
    }

    // find top n cities in the region in terms of population
    @GetMapping("/search/population/top")
    public List<Ville> getVillesTopPopulation(@RequestParam String codeDepartement, @RequestParam int n) {
        Pageable pageable = PageRequest.of(0, n); // Get the top 'n' results
        return villeRepository.findByDepartement_CodeDepartementOrderByNbHabitantsDesc(codeDepartement, pageable);
    }

    @GetMapping("/export-csv")
    public ResponseEntity<String> exportVillesToCsv() {
        Iterable<Ville> villesIterable = villeRepository.findAll();

        // Convertir l'Iterable en List pour faciliter les opérations
        List<Ville> villes = new ArrayList<>();
        villesIterable.forEach(villes::add);  // Ajout de chaque élément de l'iterable à la liste

        // Générer le contenu du fichier CSV
        String csvContent = villes.stream()
                .map(ville -> ville.getNom() + "," + ville.getNbHabitants() + "," + ville.getDepartement().getCodeDepartement() + "," + ville.getDepartement().getNomDepartement())
                .collect(Collectors.joining("\n"));

        // Ajouter un en-tête CSV
        String csvHeader = "Nom de la ville,Nombre d'habitants,Code département,Nom du département\n";

        String fullCsv = csvHeader + csvContent;

        // Configurer les en-têtes HTTP pour forcer le téléchargement du fichier CSV
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=villes.csv");

        return ResponseEntity.ok()
                .headers(headers)
                .body(fullCsv);
    }

    // Creates a city in the DB
    @PostMapping
    public ResponseEntity<?> creerVille(@RequestBody VilleDto villeDto) throws InvalidVilleException {

        // searching by codeDepartement
        Departement departement = departementRepository.findByCodeDepartement(villeDto.getCodeDepartement())
                .orElseThrow(() -> new InvalidVilleException("Le département n'existe pas."));

        DepartementDto departementDto = DepartementMapper.toDto(departement);
        Ville ville = VilleMapper.toBean(villeDto, departementDto);
        villeService.validateVille(ville, departement);
        villeRepository.save(ville);

        return ResponseEntity.status(HttpStatus.CREATED).body("La ville a été créée avec succès.");
    }


    // Updates a city in the DB
    @PutMapping("/{id}")
    public ResponseEntity<?> updateVille(@PathVariable Integer id, @RequestBody VilleDto villeDto) throws InvalidVilleException {

        // Searching by ID
        Ville existingVille = villeRepository.findById(id)
                .orElseThrow(() -> new InvalidVilleException("La ville n'existe pas."));

        // Finding departemnt by codeDepartement
        Departement departement = departementRepository.findByCodeDepartement(villeDto.getCodeDepartement())
                .orElseThrow(() -> new InvalidVilleException("Le département n'existe pas."));

        // Update
        existingVille.setNbHabitants(villeDto.getNbHabitants());
        existingVille.setDepartement(departement); // Zaktualizuj referencję do departamentu

        // Validation and save
        villeService.validateVille(existingVille, departement);
        villeRepository.save(existingVille);

        return ResponseEntity.status(HttpStatus.OK).body("La ville a été mise à jour avec succès.");
    }


    // Deletes the city from the DB
    @DeleteMapping
    public void deleteVille(@RequestParam int id) {
        villeRepository.deleteById(id);
    }

}
