package com.example.forage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.forage.DAO.StatutDAO;
import com.example.forage.model.Statut;
import com.example.forage.model.TypeDevis;

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
    public List<Statut> getAll(){
        return statutDAO.findAll();
    }
    public Statut getBonStatut(TypeDevis typeDevis,int id){
        List<Statut> allList = getAll();
        List<Statut> correspondant = new ArrayList<>();
        for (Statut statut : allList) {
            if (statut.getNom().contains(typeDevis.getNom())) {
                correspondant.add(statut);
            }
        }
        return correspondant.get(id);
    }
}
