package fr.diginamic.villes.webservice;

import fr.diginamic.villes.entities.Ville;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Rest controller returns an object or a list of objects in form of a JSON
// This will work with a URL finishing with /villes
@RestController
@RequestMapping("/villes")
public class VilleControleur {

    // getVilles answer a GET request sent by HTTP
    // Returns a list of 3 cities entered manually below
    @GetMapping
    public List<Ville> getVilles() {
        return Arrays.asList(
                new Ville("Montpellier", 270000),
                new Ville("Lille", 230000),
                new Ville("Varsovie", 1700000)
        );
    }

}
