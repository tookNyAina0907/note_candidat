package com.example.forage.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.forage.DAO.ClientDAO;
import com.example.forage.DAO.ParametreDAO_forage;
import com.example.forage.model.Client;
import com.example.forage.model.Parametre_forage;
@Service
public class ParametreService_forage {
    @Autowired
    private ParametreDAO_forage parametreDAO;
    public List<Parametre_forage> getAll(){
        return parametreDAO.findAll();
    }
}
