package com.example.forage.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.forage.model.Demande;
import org.springframework.data.repository.query.Param;
@Repository
public interface DemandeDAO extends JpaRepository<Demande, Long> {
    List<Demande> findByClientId(Long clientId);

    @Query("SELECT d FROM Demande d JOIN d.demandeStatuts ds WHERE ds.statut.id = :statutId " +
           "AND ds.dateStatut = (SELECT MAX(ds2.dateStatut) FROM DemandeStatut ds2 WHERE ds2.demande = d)")
    List<Demande> findByCurrentStatutId(@Param("statutId") Long statutId);
}
