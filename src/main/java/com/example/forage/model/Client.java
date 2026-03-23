package com.example.forage.model;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "t_client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "contact" , nullable = false , unique = true)
    private String contact;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Demande> demandes;
    
    public Client(Long id, String nom, String contact, List<Demande> demandes) {
        this.id = id;
        this.nom = nom;
        this.contact = contact;
        this.demandes = demandes;
    }

    public List<Demande> getDemandes() {
        return demandes;
    }

    public void setDemandes(List<Demande> demandes) {
        this.demandes = demandes;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Client() {
    }

    public Client(Long id, String nom, String contact) {
        this.id = id;
        this.nom = nom;
        this.contact = contact;
    }
    
}
