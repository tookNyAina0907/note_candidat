package com.example.forage.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "t_demande")
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "date_demande", nullable = false)
    private LocalDateTime dateDemande;

    @Column(name = "district", nullable = false)
    private String district;

    @OneToMany(mappedBy = "demande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Devis> devis;

    @OneToMany(mappedBy = "demande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DemandeStatut> demandeStatuts;

    @Column(name = "lieu", nullable = false)
    private String lieu;
    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Demande() {
    }

    public Demande(Long id, Client client, String description, LocalDateTime dateDemande, String district,
            List<Devis> devis, List<DemandeStatut> demandeStatuts, String lieu) {
        this.id = id;
        this.client = client;
        this.description = description;
        this.dateDemande = dateDemande;
        this.district = district;
        this.lieu = lieu;
        this.devis = devis;
        this.demandeStatuts = demandeStatuts;
        this.devis = devis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(LocalDateTime dateDemande) {
        this.dateDemande = dateDemande;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public List<Devis> getDevis() {
        return devis;
    }

    public void setDevis(List<Devis> devis) {
        this.devis = devis;
    }

    public List<DemandeStatut> getDemandeStatuts() {
        return demandeStatuts;
    }

    public void setDemandeStatuts(List<DemandeStatut> demandeStatuts) {
        this.demandeStatuts = demandeStatuts;
    }

    

}
