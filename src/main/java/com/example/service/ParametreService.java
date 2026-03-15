package com.example.service;

import com.example.dao.ParametreDAO;
import com.example.model.Matiere;
import com.example.model.Parametre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.*;
import java.util.List;

@Service
public class ParametreService {

    @Autowired
    private ParametreDAO parametreDAO;

    public List<Parametre> getAllParametres() {
        return parametreDAO.findAll();
    }

    public Parametre getParametreById(Integer id) {
        return parametreDAO.findById(id).orElse(null);
    }

    public void saveParametre(Parametre parametre) {
        parametreDAO.save(parametre);
    }

    public void deleteParametreById(Integer id) {
        parametreDAO.deleteById(id);
    }
    public List<Parametre> getParametresByMatiere(Matiere matiereId) {
        return parametreDAO.findByMatiere(matiereId);
    }
        public boolean isBonParametre(double diff, Parametre parametre) {
        boolean isBon = false;
        ExpressionParser parser = new SpelExpressionParser();
        isBon = parser
                .parseExpression(diff + " " + parametre.getOperateur().getOperateur() + " " + parametre.getSeuil())
                .getValue(Boolean.class);
        return isBon;
    }

    public List<Parametre> getLesBonParametres(double diff, List<Parametre> parametres) {
        List<Parametre> bonParametres = new java.util.ArrayList<>();
        for (Parametre parametre : parametres) {
            if (isBonParametre(diff, parametre)) {
                bonParametres.add(parametre);
            }
        }
        return bonParametres;
    }

    public Parametre getParametreMinSeuil(List<Parametre> parametres) {
        Parametre parametreMin = parametres.get(0);
        for (int i = 1; i < parametres.size(); i++) {
            if (parametres.get(i).getSeuil() < parametreMin.getSeuil()) {
                parametreMin = parametres.get(i);
            }
        }
        return parametreMin;
    }

    public Parametre getBonParametre(double diff, List<Parametre> parametres) {
        Parametre parametre = new Parametre();
        List<Parametre> bonParametres = getLesBonParametres(diff, parametres);
        if (!bonParametres.isEmpty()) {
            if (bonParametres.size() == 1) {
                parametre = bonParametres.get(0);
            } else {
                List<Parametre> bonParametresMin = NbCompteMin(diff, bonParametres);
                if (bonParametresMin.size() == 1) {
                    parametre = bonParametresMin.get(0);
                }
                else{
                    parametre = getParametreMinSeuil(bonParametresMin);
                }
            }
        }
        return parametre;
    }
    


    public double getMinCompteParametre(double diff, List<Parametre> parametres) {
        // Parametre parametre = new Parametre();
        double min = Math.abs(parametres.get(0).getSeuil() - diff);
        for (int i = 0; i < parametres.size(); i++) {
            double current = Math.abs(parametres.get(i).getSeuil() - diff);
            if (current < min) {
                min = current;
            }
        }
        return min;
    }

    public List<Parametre> NbCompteMin(double diff, List<Parametre> parametres) {
        List<Parametre> bonParametres = new ArrayList<>();
        double min = getMinCompteParametre(diff, parametres);
        for (int i = 0; i < parametres.size(); i++) {
            double current = Math.abs(parametres.get(i).getSeuil() - diff);
            if (current == min) {
                bonParametres.add(parametres.get(i));
            }
        }
        return bonParametres;
    }
}
