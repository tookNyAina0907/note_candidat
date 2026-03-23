package com.example.forage.DAO;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.forage.model.*;

@Repository
public interface StatutDAO extends JpaRepository<Statut, Long> {

    Statut findByNom(String nom);
    
}
