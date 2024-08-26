package fr.diginamic.villes.repository;

import fr.diginamic.villes.model.Departement;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DepartementRepository extends CrudRepository<Departement, Integer> {

    Optional<Departement> findByCodeDepartement(String codeDepartement);

}
