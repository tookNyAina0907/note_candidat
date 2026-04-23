package com.example.forage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.forage.DAO.DemandeDAO;
import com.example.forage.model.*;
@Service
public class DemandeService {
    @Autowired
    private DemandeDAO demandeDAO;

    public Demande getDemandeById(Long id) {
        if (id == null) {
            return null;
            
        }
        return demandeDAO.findById(id).orElse(null);
    }
    public Demande saveDemande(Demande demande) {
        return demandeDAO.save(demande);
    }
    public void deleteDemande(Long id) {
        demandeDAO.deleteById(id);
    }
    public void updateDemande(Long id, Demande demandeDetails) {
        Demande demande = demandeDAO.findById(id).orElse(null);
        if (demande != null) {
            demande.setClient(demandeDetails.getClient());
            demande.setDateDemande(demandeDetails.getDateDemande());
            demande.setDistrict(demandeDetails.getDistrict());
            demande.setDescription(demandeDetails.getDescription());
            demande.setLieu(demandeDetails.getLieu());
            saveDemande(demande);
        }
    }
    public List<Demande> getAllDemandes() {
        return demandeDAO.findAll();
    }
    public List<Demande> getDemandesByClientId(Long clientId) {
        return demandeDAO.findByClientId(clientId);
    }
    public List<Demande> getDemandesByCurrentStatutId(Long statutId) {
        return demandeDAO.findByCurrentStatutId(statutId);
    }

}
