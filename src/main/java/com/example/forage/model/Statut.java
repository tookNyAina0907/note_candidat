package com.example.forage.model;
import java.util.*;
import jakarta.persistence.*;
@Entity
@Table(name = "t_statut")
public class Statut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false, unique = true)
    private String nom;

    @OneToMany(mappedBy = "statut", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DemandeStatut> demandeStatuts;

    public Statut() {
    }

    public Statut(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public java.util.List<DemandeStatut> getDemandeStatuts() {
        return demandeStatuts;
    }

    public void setDemandeStatuts(java.util.List<DemandeStatut> demandeStatuts) {
        this.demandeStatuts = demandeStatuts;
    }
}
