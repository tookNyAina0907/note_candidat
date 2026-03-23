package com.example.forage.model;

// import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

// import jakarta.annotation.Generated;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_devis")
public class Devis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="date_devis", nullable = false)
    private LocalDateTime dateDevis;

    @ManyToOne
    @JoinColumn(name = "demande_id", nullable = false)
    private Demande demande;

    @ManyToOne
    @JoinColumn(name = "type_devis_id", nullable = false)
    private TypeDevis typeDevis;

    @OneToMany(mappedBy = "devis", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetailDevis> detailDevis;

    // public Devis(Long id, LocalDateTime dateDevis, Demande demande, TypeDevis typeDevis) {
    //     this.id = id;
    //     this.dateDevis = dateDevis;
    //     this.demande = demande;
    //     this.typeDevis = typeDevis;
    // }

    public Devis(Long id, LocalDateTime dateDevis, Demande demande, TypeDevis typeDevis,
            List<DetailDevis> detailDevis) {
        this.id = id;
        this.dateDevis = dateDevis;
        this.demande = demande;
        this.typeDevis = typeDevis;
        this.detailDevis = detailDevis;
    }

    public List<DetailDevis> getDetailDevis() {
        return detailDevis;
    }

    public void setDetailDevis(List<DetailDevis> detailDevis) {
        this.detailDevis = detailDevis;
    }

    public Devis() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateDevis() {
        return dateDevis;
    }

    public void setDateDevis(LocalDateTime dateDevis) {
        this.dateDevis = dateDevis;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public TypeDevis getTypeDevis() {
        return typeDevis;
    }

    public void setTypeDevis(TypeDevis typeDevis) {
        this.typeDevis = typeDevis;
    }

    public Devis(Long id, LocalDateTime dateDevis, Demande demande, TypeDevis typeDevis) {
        this.id = id;
        this.dateDevis = dateDevis;
        this.demande = demande;
        this.typeDevis = typeDevis;
    }

    public Long getId() {
        return id;
    }
    
}
