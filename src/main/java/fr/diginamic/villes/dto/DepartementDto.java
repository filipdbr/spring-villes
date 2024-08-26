package fr.diginamic.villes.dto;

public class DepartementDto {

    // attributs given in the task

    private String codeDepartement;
    private String nomDepartement;
    private long nbHabitants;

    // getters and setters

    public String getCodeDepartement() {
        return codeDepartement;
    }

    public void setCodeDepartement(String codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    public String getNomDepartement() {
        return nomDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    public long getNbHabitants() {
        return nbHabitants;
    }

    public void setNbHabitants(long nbHabitants) {
        this.nbHabitants = nbHabitants;
    }
}
