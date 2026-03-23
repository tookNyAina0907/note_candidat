package com.example.forage.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.forage.model.Demande;
@Repository
public interface DemandeDAO extends JpaRepository<Demande, Long> {
    
}
