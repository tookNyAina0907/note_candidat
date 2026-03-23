package com.example.forage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.forage.DAO.StatutDAO;
import com.example.forage.model.Statut;

@Service
public class StatutService {
    @Autowired
    private StatutDAO statutDAO;

    public Statut getStatutById(Long id) {
        return statutDAO.findById(id).orElse(null);
    }
    public void saveStatut(Statut statut){
        statutDAO.save(statut);
    }
    public void deleteStatut(Long id){
        statutDAO.deleteById(id);
    }
    public Statut getStatutByNom(String nom){
        return statutDAO.findByNom(nom);
    }
}
