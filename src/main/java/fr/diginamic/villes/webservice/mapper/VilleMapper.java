package fr.diginamic.villes.webservice.mapper;

import fr.diginamic.villes.model.Ville;
import fr.diginamic.villes.webservice.dto.DepartementDto;
import fr.diginamic.villes.webservice.dto.VilleDto;

public class VilleMapper {

    /**
     * Converts a Ville entity to VilleDto.
     *
     * @param ville The Ville entity to be converted.
     * @return VilleDto The DTO version of the Ville entity.
     */
    public static VilleDto toDto(Ville ville) {
        VilleDto villeDto = new VilleDto();
        villeDto.setCodeVille(ville.getId());
        villeDto.setNbHabitants(ville.getNbHabitants());

        // Convert the associated department to DTO
        if (ville.getDepartement() != null) {
            DepartementDto departementDto = new DepartementDto();
            departementDto.setCodeDepartement(ville.getDepartement().getId());
            departementDto.setNomDepartement(ville.getDepartement().getNomDepartement());
        }

        return villeDto;
    }

    /**
     * Converts a VilleDto to Ville entity.
     *
     * @param villeDto The VilleDto to be converted to an entity.
     * @param departementDto The associated DepartementDto.
     * @return Ville The entity version of VilleDto.
     */
    public Ville toBean(VilleDto villeDto, DepartementDto departementDto) {
        Ville ville = new Ville();
        ville.setId(villeDto.getCodeVille());
        ville.setNbHabitants(villeDto.getNbHabitants());

        if (departementDto != null) {
            ville.setDepartement(DepartementMapper.toBean(departementDto));
        }

        return ville;
    }

}
