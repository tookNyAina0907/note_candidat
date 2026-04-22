package com.example.forage.DAO;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.forage.model.DemandeStatut;

@Repository
public interface DemandeStatutDAO extends JpaRepository<DemandeStatut, Long> {

    List<DemandeStatut> findByDemandeIdAndDateStatutBetween(Long demandeId, LocalDateTime start, LocalDateTime end);
    @Query("SELECT COUNT(*) FROM DemandeStatut ds WHERE ds.statut.id = :statutId")
    Long countByStatutId(Long statutId);

}
