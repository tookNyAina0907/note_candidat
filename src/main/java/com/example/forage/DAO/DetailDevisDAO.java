package com.example.forage.DAO;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.forage.model.*;

@Repository
public interface DetailDevisDAO extends JpaRepository<DetailDevis, Long> {
    @Query("SELECT SUM(d.prix*d.quantite) as devis_total FROM DetailDevis d")
    Double devistotal();
}
