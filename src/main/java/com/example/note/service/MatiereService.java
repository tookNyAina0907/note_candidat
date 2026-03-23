package com.example.note.service;

import com.example.note.dao.*;
import com.example.note.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatiereService {

    @Autowired
    private MatiereDAO matiereDAO;

    public List<Matiere> getAllMatieres() {
        return matiereDAO.findAll();
    }

    public Matiere getMatiereById(Integer id) {
        return matiereDAO.findById(id).orElse(null);
    }

    public void saveMatiere(Matiere matiere) {
        matiereDAO.save(matiere);
    }

    public void deleteMatiereById(Integer id) {
        matiereDAO.deleteById(id);
    }
}
