package fr.diginamic.villes.web;

import fr.diginamic.villes.dto.VilleDto;
import fr.diginamic.villes.model.Departement;
import fr.diginamic.villes.repository.DepartementRepository;
import fr.diginamic.villes.repository.VilleRepository;
import fr.diginamic.villes.service.VilleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VilleControleur.class)
public class VilleControleurTest {

    // Injects MockMvc to simulate HTTP requests in tests
    @Autowired
    private MockMvc mockMvc;

    // Mocking VilleService to avoid real database interactions
    @MockBean
    private VilleService villeService;

    // Mocking VilleRepository for testing
    @MockBean
    private VilleRepository villeRepository;

    // Mocking DepartementRepository for testing
    @MockBean
    private DepartementRepository departementRepository;

    // Test for successfully creating a new city
    @Test
    public void testCreateVilleSuccess() throws Exception {
        VilleDto villeDto = new VilleDto();
        villeDto.setNom("Paris");
        villeDto.setNbHabitants(1000000);
        villeDto.setCodeDepartement("75");

        // Simulates finding the department by its code
        when(departementRepository.findByCodeDepartement("75")).thenReturn(java.util.Optional.of(new Departement()));

        // Simulates HTTP POST request and verifies the response
        mockMvc.perform(post("/villes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\":\"Paris\",\"nbHabitants\":1000000,\"codeDepartement\":\"75\"}"))
                .andExpect(status().isCreated())  // Expect HTTP 201 status
                .andExpect(MockMvcResultMatchers.content().string("La ville a été créée avec succès."));
    }

    // Test for handling invalid city data
    @Test
    public void testCreateVilleInvalidData() throws Exception {
        // Simulates HTTP POST request with invalid data and checks for bad request status
        mockMvc.perform(post("/villes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\":\"P\",\"nbHabitants\":5,\"codeDepartement\":\"7\"}"))
                .andExpect(status().isBadRequest());  // Expect HTTP 400 status
    }
}
