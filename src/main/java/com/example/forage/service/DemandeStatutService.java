package com.example.forage.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.forage.DAO.DemandeStatutDAO;
import com.example.forage.model.*;

@Service
public class DemandeStatutService {
    @Autowired
    private DemandeStatutDAO demandeStatutDAO;

    public void saveDemandeStatut(DemandeStatut demandeStatut){
        demandeStatutDAO.save(demandeStatut);
    }

    public List<DemandeStatut> getFilteredStatuts(Long demandeId, LocalDateTime start, LocalDateTime end) {
        if (start != null && end != null) {
            return demandeStatutDAO.findByDemandeIdAndDateStatutBetween(demandeId, start, end);
        }
        return null;
    }

    public DemandeStatut findById(Long id) {
        return demandeStatutDAO.findById(id).orElse(null);
    }
    public Long countByStatutId(Long statutId) {
        return demandeStatutDAO.countByStatutId(statutId);
    }
    public List<DemandeStatut> findByStatutId(Long statutId) {
        return demandeStatutDAO.findByStatutId(statutId);
    }
}
