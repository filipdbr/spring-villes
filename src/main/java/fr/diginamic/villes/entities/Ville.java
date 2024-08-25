package fr.diginamic.villes.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Column(name="id")
    private int id;

    @Column(name="nom")
    private String nom;

    @Column(name="nb_habitants")
    private long nbHabitants;

    @ManyToOne
    @JoinColumn(name="code_departement")
    @JsonBackReference
    private Departement departement;

    // Contructeur vide pour JPA
    public Ville() {

    }

    // Constructeur
    public Ville(String nom, int nbHabitants, Departement departement) {
        // Increment the global counter for each new instance
        // Assign the unique Id to each new instance
        this.nom = nom;
        this.nbHabitants = nbHabitants;
        this.departement = departement;
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

    public long getNbHabitants() {
        return nbHabitants;
    }

    public void setNbHabitants(long nbHabitants) {
        this.nbHabitants = nbHabitants;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
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
