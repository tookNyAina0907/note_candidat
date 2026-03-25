package com.example.forage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.forage.DAO.DevisDAO;
import com.example.forage.model.*;
import java.util.List;

@Service
public class DevisService {
    @Autowired
    private DevisDAO devisDAO;
    
    public Devis saveDevis(Devis devis){
        return devisDAO.save(devis);
    }
    public Devis getDevisById(Long id){
        return devisDAO.findById(id).orElse(null);
    }
    public void deleteDevis(Long id){
        devisDAO.deleteById(id);
    }
    public Devis updateDevis(Devis devis){
        return devisDAO.save(devis);
    }
    public List<Devis> getDevisByDemande(Demande demande){
        return devisDAO.findByDemande(demande);
    }
}
