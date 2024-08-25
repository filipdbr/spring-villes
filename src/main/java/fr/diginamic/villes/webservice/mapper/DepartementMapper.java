package fr.diginamic.villes.webservice.mapper;

import fr.diginamic.villes.entities.Departement;
import fr.diginamic.villes.entities.Ville;
import fr.diginamic.villes.webservice.dto.DepartementDto;


public class DepartementMapper {

    /**
     * Converts a Departement entity to a DepartementDto.
     *
     * @param departement The Departement entity to be converted.
     * @return DepartementDto The DTO version of the Departement entity.
     */
    public static DepartementDto toDto(Departement departement) {
        DepartementDto departementDto = new DepartementDto();
        departementDto.setCodeDepartement(departement.getId());
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
        departement.setId(departementDto.getCodeDepartement());
        departement.setNomDepartement(departementDto.getNomDepartement());
        return departement;
    }

}
