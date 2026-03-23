package com.example.forage.DAO;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.forage.model.Devis;
import org.springframework.stereotype.Repository;

@Repository

public interface DevisDAO extends JpaRepository<Devis, Long> {
    
}
