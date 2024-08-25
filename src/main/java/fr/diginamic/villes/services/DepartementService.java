package fr.diginamic.villes.services;

import fr.diginamic.villes.model.Departement;
import fr.diginamic.villes.model.Ville;
import fr.diginamic.villes.interfaces.DepartementRepository;
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

}
