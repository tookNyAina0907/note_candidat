package com.example.note.service;

import com.example.note.dao.*;
import com.example.note.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperateurService {

    @Autowired
    private OperateurDAO operateurDAO;

    public List<Operateur> getAllOperateurs() {
        return operateurDAO.findAll();
    }

    public Operateur getOperateurById(Integer id) {
        return operateurDAO.findById(id).orElse(null);
    }

    public void saveOperateur(Operateur operateur) {
        operateurDAO.save(operateur);
    }

    public void deleteOperateurById(Integer id) {
        operateurDAO.deleteById(id);
    }
}
