package fr.diginamic.villes.interfaces;

import fr.diginamic.villes.entities.Departement;
import fr.diginamic.villes.entities.Ville;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface DepartementRepository extends CrudRepository<Departement, Integer> {

}
