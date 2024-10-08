package fr.diginamic.villes.mapper;

import fr.diginamic.villes.model.Departement;
import fr.diginamic.villes.model.Ville;
import fr.diginamic.villes.dto.DepartementDto;


public class DepartementMapper {

    /**
     * Converts a Departement entity to a DepartementDto.
     *
     * @param departement The Departement entity to be converted.
     * @return DepartementDto The DTO version of the Departement entity.
     */
    public static DepartementDto toDto(Departement departement) {
        DepartementDto departementDto = new DepartementDto();
        departementDto.setCodeDepartement(departement.getCodeDepartement());
        departementDto.setNomDepartement(departement.getNomDepartement());
        departementDto.setNbHabitants(departement.getVilles().stream().mapToLong(Ville::getNbHabitants).sum());
        return departementDto;
    }

    /**
     * Converts a DepartementDto back to a Departement entity (Bean).
     *
     * @param departementDto The DTO to be converted back into a Departement entity.
     * @return Departement The entity version of the DepartementDto.
     */
    public static Departement toBean(DepartementDto departementDto) {
        Departement departement = new Departement();
        departement.setCodeDepartement(departementDto.getCodeDepartement());
        departement.setNomDepartement(departementDto.getNomDepartement());
        return departement;
    }

}
