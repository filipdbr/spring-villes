package fr.diginamic.villes.entities;

// POJO ville (cities)
public class Ville {

    // Attributs
    private String nom;
    private int nbHabitatants;

    // Constructeur
    public Ville(String nom, int nbHabitatants) {
        this.nom = nom;
        this.nbHabitatants = nbHabitatants;
    }

    // Méthodes
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbHabitatants() {
        return nbHabitatants;
    }

    public void setNbHabitatants(int nbHabitatants) {
        this.nbHabitatants = nbHabitatants;
    }

    // Override of the toString method
    @Override
    public String toString() {
        return "Ville{" +
                "nom='" + nom + '\'' +
                ", nbHabitatants=" + nbHabitatants +
                '}';
    }

}
