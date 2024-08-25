package fr.diginamic.villes.interfaces;

import fr.diginamic.villes.entities.Departement;
import org.springframework.data.repository.CrudRepository;

public interface DepartementRepository extends CrudRepository<Departement, Integer> {

    Departement findByNomDepartement(String nomDepartement);

}
