package com.example.forage.DAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.forage.model.*;
import java.util.List;

@Repository

public interface DevisDAO extends JpaRepository<Devis, Long> {
    List<Devis> findByDemande(Demande demande);
    
}
