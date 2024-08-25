package fr.diginamic.villes.dto;

public class DepartementDto {

    // attributs given in the task

    private String codeDepartement;
    private Integer nomDepartement;
    private long nbHabitants;

    // getters and setters

    public String getCodeDepartement() {
        return codeDepartement;
    }

    public void setCodeDepartement(String codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    public Integer getNomDepartement() {
        return nomDepartement;
    }

    public void setNomDepartement(Integer nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    public long getNbHabitants() {
        return nbHabitants;
    }

    public void setNbHabitants(long nbHabitants) {
        this.nbHabitants = nbHabitants;
    }
}
