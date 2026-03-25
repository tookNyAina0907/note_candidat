package com.example.forage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.forage.DAO.TypeDevisDAO;
import com.example.forage.model.TypeDevis;

@Service
public class TypeDevisService {
    @Autowired
    private TypeDevisDAO typeDevisDAO;

    public TypeDevis saveTypeDevis(TypeDevis typeDevis) {
        return typeDevisDAO.save(typeDevis);
    }

    public TypeDevis getTypeDevisById(Long id) {
        return typeDevisDAO.findById(id).orElse(null);
    }

    public void deleteTypeDevis(Long id) {
        typeDevisDAO.deleteById(id);
    }

    public TypeDevis updateTypeDevis(TypeDevis typeDevis) {
        return typeDevisDAO.save(typeDevis);
    }

    public List<TypeDevis> getAllTypeDevis() {
        return typeDevisDAO.findAll();
    }
}
