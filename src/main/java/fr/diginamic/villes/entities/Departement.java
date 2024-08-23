package fr.diginamic.villes.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

// Marks the class as an entity for JPA
@Entity
@Table(name="departement")
public class Departement {

    // Primary key with auto-increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="code_departement")
    private Integer codeDepartement;

    // Column for department name
    @Column(name="nom_departement")
    private String nomDepartement;

    // One-to-many relationship with Ville, mapped by the 'departement' field in Ville
    @OneToMany(mappedBy = "departement")
    private Set<Ville> villes;

    // Default constructor
    public Departement() {
    }

    // Constructor with department name initialization
    public Departement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
        this.villes = new HashSet<Ville>(); // Initializes the set of cities
    }

    // Getters and setters for id
    public Integer getId() {
        return codeDepartement;
    }

    public void setId(Integer id) {
        this.codeDepartement = id;
    }

    // Getters and setters for department name
    public String getNomDepartement() {
        return nomDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    // Getters and setters for cities
    public Set<Ville> getVilles() {
        return villes;
    }

    public void setVilles(Set<Ville> villes) {
        this.villes = villes;
    }

    // Override toString method for better object representation
    @Override
    public String toString() {
        return "Departement{" +
                "codeDepartement=" + codeDepartement +
                ", nomDepartement='" + nomDepartement + '\'' +
                ", villes=" + villes +
                '}';
    }
}
