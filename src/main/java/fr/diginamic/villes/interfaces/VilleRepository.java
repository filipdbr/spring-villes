package fr.diginamic.villes.interfaces;

import fr.diginamic.villes.entities.Ville;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VilleRepository  extends CrudRepository<Ville, Integer> {

    // finds a city by its name. The methods in Interfaces are public by default.
    Ville findByNom(String nom);

    // finds cities by the first letter of its name.
    Iterable<Ville> findByNomStartsWith(char firstLetter);

    // finds cities with population greater then nbHabitants
    Iterable<Ville> findByNbHabitantsGreaterThan(long nbHabitants);

    // finds all cities in department with population higher then
    Iterable<Ville> findByDepartement_CodeDepartementAndNbHabitantsGreaterThan(Integer codeDepartement, long nbHabitants);

    // Recherche de toutes les villes d’un département dont la population est supérieure à min et inférieure à max.
    Iterable<Ville> findByDepartement_CodeDepartementAndNbHabitantsBetween(int codeDepartementA, long minNbHabitants, long maxNbHabitants);

    // Recherche des n villes les plus peuplées d’un département donné (n est aussi un paramètre)
    List<Ville> findByDepartement_CodeDepartementOrderByNbHabitantsDesc(int codeDepartement, Pageable pageable);

}
