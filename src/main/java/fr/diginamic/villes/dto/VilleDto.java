package fr.diginamic.villes.dto;

public class VilleDto {

    // attributs given in the task

    private Integer codeVille;
    private long nbHabitants;
    private String codeDepartement;
    private String nomDepartement;
    private String nom;

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

    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }
}
