package com.example.forage.model;

import jakarta.persistence.*;
// import java.util.*;


@Entity
@Table(name = "t_detail_devis")
public class DetailDevis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "devis_id", nullable = false)
    private Devis devis;

    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Column(name = "prix" , nullable = false)
    private Double prix;

    public DetailDevis() {
    }

    public DetailDevis(Long id, Devis devis, String libelle, Double prix) {
        this.id = id;
        this.devis = devis;
        this.libelle = libelle;
        this.prix = prix;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    
}
