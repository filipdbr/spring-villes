package fr.diginamic.villes.webservice.dto;

public class VilleDto {

    // attributs given in the task

    private Integer codeVille;
    private long nbHabitants;
    private Integer codeDepartement;
    private String nomDepartement;

    // getters and setters

    public Integer getCodeVille() {
        return codeVille;
    }

    public void setCodeVille(Integer codeVille) {
        this.codeVille = codeVille;
    }

    public long getNbHabitants() {
        return nbHabitants;
    }

    public void setNbHabitants(long nbHabitants) {
        this.nbHabitants = nbHabitants;
    }

    public Integer getCodeDepartement() {
        return codeDepartement;
    }

    public void setCodeDepartement(Integer codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    public String getNomDepartement() {
        return nomDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }
}
