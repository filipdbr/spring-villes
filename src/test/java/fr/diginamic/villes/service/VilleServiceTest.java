package fr.diginamic.villes.service;

import fr.diginamic.villes.model.Departement;
import fr.diginamic.villes.model.Ville;
import fr.diginamic.villes.repository.VilleRepository;
import fr.diginamic.villes.exception.InvalidVilleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
@ActiveProfiles("test")
public class VilleServiceTest {

    // Autowires VilleRepository to interact with the H2 test database
    @Autowired
    private VilleRepository villeRepository;

    private VilleService villeService;

    // Initializes VilleService before each test
    @BeforeEach
    public void setup() {
        villeService = new VilleService(villeRepository);
    }

    // Test adding a new city and ensuring it's saved in the database
    @Test
    public void testAddNewVille() throws InvalidVilleException {
        Ville ville = new Ville();
        ville.setNom("Varsovie");
        ville.setNbHabitants(2000000);

        // Validates the city data
        villeService.validateVille(ville, new Departement());

        // Saves the city to the repository
        villeRepository.save(ville);

        // Asserts that the city was saved correctly
        assertNotNull(villeRepository.findByNom("Paris"));
    }

    // Test that adding a duplicate city throws an exception
    @Test
    public void testAddDuplicateVilleThrowsException() {
        Ville ville = new Ville();
        ville.setNom("Varsovie");
        ville.setNbHabitants(2000000);

        // Saves the first city instance
        villeRepository.save(ville);

        // Tries to save a duplicate city and expects an InvalidVilleException
        Ville duplicateVille = new Ville();
        duplicateVille.setNom("Varsovie");
        duplicateVille.setNbHabitants(500000);

        assertThrows(InvalidVilleException.class, () -> {
            villeService.validateVille(duplicateVille, new Departement());
        });
    }
}
