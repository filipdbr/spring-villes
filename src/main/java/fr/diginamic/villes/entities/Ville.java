package fr.diginamic.villes.entities;

// POJO ville (cities)
public class Ville {

    // Attributs
    // Unique id
    private int id;
    // Global counter of all instances. The first instance is 1.
    private static int idCounter = 1;
    private String nom;
    private int nbHabitatants;

    // Constructeur
    public Ville(String nom, int nbHabitatants) {
        // Increment the global counter for each new instance
        // Assign the unique Id to each new instance
        this.id = idCounter++;
        this.nom = nom;
        this.nbHabitatants = nbHabitatants;
    }

    // Méthodes

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
