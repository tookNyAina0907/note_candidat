package com.example.forage.model;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "t_parametre")
public class Parametre_forage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "remise", nullable = false)
    private double nom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getNom() {
        return nom;
    }

    public void setNom(double nom) {
        this.nom = nom;
    }

    public Parametre_forage(Long id, double nom) {
        this.id = id;
        this.nom = nom;
    }

    public Parametre_forage() {
    }
}
