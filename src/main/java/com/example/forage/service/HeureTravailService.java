package com.example.forage.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.forage.DAO.HeureTravailDAO;
import com.example.forage.model.HeureTravail;

@Service
public class HeureTravailService {
    @Autowired
    private HeureTravailDAO heureTravailDAO;

    public HeureTravail getHeureTravail() {
        return heureTravailDAO.findAll().get(0);
    }

    public HeureTravail getHeureTravailById(Long id) {
        return heureTravailDAO.findById(id).orElse(null);
    }

    public void saveHeureTravail(HeureTravail heureTravail){
        heureTravailDAO.save(heureTravail);
    }

    public void deleteHeureTravail(Long id){
        heureTravailDAO.deleteById(id);
    }
}
