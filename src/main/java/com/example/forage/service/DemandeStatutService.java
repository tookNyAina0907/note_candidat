package com.example.forage.service;

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
    // public DemandeStatut getBonStatut(Long id){
        
    // }
}
