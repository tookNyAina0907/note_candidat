package com.example.forage.model;

import jakarta.persistence.*;
import java.util.*;
@Entity
@Table(name = "t_type_devis")
public class TypeDevis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false, unique = true)
    private String nom;

    @OneToMany(mappedBy = "typeDevis", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Devis> devis;


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

    public List<Devis> getDevis() {
        return devis;
    }

    public void setDevis(List<Devis> devis) {
        this.devis = devis;
    }

    public TypeDevis() {
    }

    public TypeDevis(Long id, String nom, List<Devis> devis) {
        this.id = id;
        this.nom = nom;
        this.devis = devis;
    }
    
}
