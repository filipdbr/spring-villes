package fr.diginamic.villes.repository;

import fr.diginamic.villes.model.Departement;
import org.springframework.data.repository.CrudRepository;

public interface DepartementRepository extends CrudRepository<Departement, Integer> {

    Departement findByNomDepartement(String nomDepartement);

}
