package fr.diginamic.villes.service;

import fr.diginamic.villes.dto.DepartementDto;
import fr.diginamic.villes.exception.InvalidDepartementException;
import fr.diginamic.villes.model.Departement;
import fr.diginamic.villes.model.Ville;
import fr.diginamic.villes.repository.DepartementRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartementService {

    private final DepartementRepository departementRepository;

    public DepartementService(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    // Update departement (UPDATE)
    public Departement updateDepartement(int id, Departement newDepartement) {
        Departement departement = departementRepository.findById(id).orElse(null);
        if (departement == null) {
            return null;
        }
        departement.setNomDepartement(newDepartement.getNomDepartement());

        departement.getVilles().clear();
        for (Ville ville : departement.getVilles()) {
            ville.setDepartement(departement);
            departement.addVille(ville);
        }

        return departementRepository.save(departement);

    }

    // Validation of 2 conditions while creation of a departement
    public void validateDepartement(DepartementDto departementDto) throws InvalidDepartementException {

        String codeDepartement = departementDto.getCodeDepartement();

        if (codeDepartement.length() < 2 || codeDepartement.length() > 3) {
            throw new InvalidDepartementException("Le code département fait au maximum 3 caractères et au minimum 2");
        }

        if (departementDto.getNomDepartement() == null || codeDepartement.length() < 3) {
            throw new InvalidDepartementException("Le nom du département est obligatoire et comporte au moins 3 lettres");
        }
    }

}
