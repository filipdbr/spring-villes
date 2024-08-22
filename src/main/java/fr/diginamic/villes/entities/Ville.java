package fr.diginamic.villes.entities;

import jakarta.persistence.*;

// Marked the class as entity in JPA
// Added a name for the table which was optional
@Entity
@Table(name="ville")
public class Ville {

    // Attributs
    // Id will be unique due to GeneratedValue strategy set to auto
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @Column(name="NOM")
    private String nom;

    @Column(name="NB_HABITANTS")
    private int nbHabitants;

    // Contructeur vide pour JPA
    public Ville() {

    }

    // Constructeur
    public Ville(String nom, int nbHabitants) {
        // Increment the global counter for each new instance
        // Assign the unique Id to each new instance
        this.nom = nom;
        this.nbHabitants = nbHabitants;
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

    public int getNbHabitants() {
        return nbHabitants;
    }

    public void setNbHabitants(int nbHabitants) {
        this.nbHabitants = nbHabitants;
    }

    // Override of the toString method
    @Override
    public String toString() {
        return "Ville{" +
                "nom='" + nom + '\'' +
                ", nbHabitants=" + nbHabitants +
                '}';
    }

}
