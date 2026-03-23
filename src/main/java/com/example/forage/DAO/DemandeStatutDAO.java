package com.example.forage.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.forage.model.DemandeStatut;

@Repository
public interface DemandeStatutDAO extends JpaRepository<DemandeStatut, Long> {
    
}
