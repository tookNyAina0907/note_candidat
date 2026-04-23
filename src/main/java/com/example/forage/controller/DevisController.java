package com.example.forage.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.forage.DAO.DetailDevisDAO;
import com.example.forage.DAO.ParametreDAO_forage;
import com.example.forage.model.*;
import com.example.forage.service.*;

@Controller
@RequestMapping("/forage/devis")
public class DevisController {

    @Autowired
    private DevisService devisService;

    @Autowired
    private TypeDevisService typeDevisService;

    @Autowired
    private DemandeService demandeService;

    @Autowired
    private StatutService statutService;
    @Autowired
    private DemandeStatutService demandeStatutService;

    // @Autowired 
    // private DetailDevisDAO detailDevisDAO;

    @Autowired
    private ParametreDAO_forage parametreDAO;

    @GetMapping("/form")
    public String showForm(@RequestParam(value = "demandeId", required = false) Long demandeId, Model model) {
        model.addAttribute("devis", new Devis());
        model.addAttribute("typeDevisList", typeDevisService.getAllTypeDevis());
        if (demandeId != null) {
            model.addAttribute("prefilledDemandeId", demandeId);
        }
        return "forage/devis/form";
    }

    @PostMapping("/add")
    public String addDevis(@RequestParam("demandeId") Long demandeId,
                           @RequestParam("typeDevisId") Long typeDevisId,
                           @RequestParam("libelle[]") String[] libelles,
                           @RequestParam("prix[]") Double[] prixs,
                           @RequestParam("qte[]") Double[] qtes) {
        
        Demande demande = demandeService.getDemandeById(demandeId);
        TypeDevis typeDevis = typeDevisService.getTypeDevisById(typeDevisId);
        
        if (demande == null || typeDevis == null) {
            return "redirect:/forage/devis/form?error=invalid_data";
        }

        Devis devis = new Devis();
        devis.setDemande(demande);
        devis.setTypeDevis(typeDevis);
        devis.setDateDevis(LocalDateTime.now());
        
        List<DetailDevis> details = new ArrayList<>();
        Parametre_forage parametre = parametreDAO.findAll().get(0);
        if (libelles != null && prixs != null && qtes != null && 
            libelles.length == prixs.length && libelles.length == qtes.length) {
            for (int i = 0; i < libelles.length; i++) {
                double prixremise = 0;
                if (prixs[i]>=1000000) {
                    prixremise = prixs[i] - (prixs[i]*parametre.getNom())/100;
                }else{
                    prixremise = prixs[i];
                }
                DetailDevis detail = new DetailDevis();
                detail.setLibelle(libelles[i]);
                detail.setPrix(prixremise);
                detail.setQuantite(qtes[i]);
                detail.setDevis(devis);
                details.add(detail);
            }
        }
        devis.setDetailDevis(details);
        Statut goodStatut = statutService.getBonStatut(typeDevis,0);
        devisService.saveDevis(devis);
        DemandeStatut demandeStatut = new DemandeStatut();
        demandeStatut.setDateStatut(LocalDateTime.now());
        demandeStatut.setDemande(demande);
        demandeStatut.setStatut(goodStatut);
        demandeStatutService.saveDemandeStatut(demandeStatut);
        
        return "redirect:/forage/demande"; 
    }

    @GetMapping({"list","/list/{demandeId}"})
    public String listDevisByDemande(@PathVariable(value = "demandeId",required = false) Optional<Long> demandeId, Model model) {
        Demande demande = demandeService.getDemandeById(demandeId.orElse(null));
        // demandeService.sortStatutByDate(demande);
        List<Devis> devisList = new ArrayList<>();
        if (demande == null) {

            // return "redirect:/forage/demande?error=not_found";
            devisList = devisService.getAllDevis();
            
            model.addAttribute("devisList", devisList);
        }else{
            devisList = devisService.getDevisByDemande(demande);
            model.addAttribute("devisList", devisList);
            model.addAttribute("demande", demande);
        }
        
        // List<Devis> devisList = devisService.getDevisByDemande(demande);
        // model.addAttribute("devisList", devisList);
        return "forage/devis/list";
    }

    @GetMapping("/valider/{demandeId}/{devisId}")
    public String validerDevis(@PathVariable("demandeId") Long demandeId, @PathVariable("devisId") Long devisId, Model model) {
        Demande demande = demandeService.getDemandeById(demandeId);
        Devis devis = devisService.getDevisById(devisId);
        TypeDevis typeDevis = devis.getTypeDevis();
        Statut goodStatut = statutService.getBonStatut(typeDevis,1);
        DemandeStatut demandeStatut = new DemandeStatut();
        demandeStatut.setDateStatut(LocalDateTime.now());
        demandeStatut.setDemande(demande);
        demandeStatut.setStatut(goodStatut);
        demandeStatutService.saveDemandeStatut(demandeStatut);
        

        if (demande == null) {
            return "redirect:/forage/demande?error=not_found";
        }
        return "redirect:/forage/devis/list/"+demandeId;
    }
    
    @GetMapping("/refuser/{demandeId}/{devisId}")
    public String refuserDevis(@PathVariable("demandeId") Long demandeId, @PathVariable("devisId") Long devisId, Model model) {
        Demande demande = demandeService.getDemandeById(demandeId);
        Devis devis = devisService.getDevisById(devisId);
        TypeDevis typeDevis = devis.getTypeDevis();
        Statut goodStatut = statutService.getBonStatut(typeDevis,2);
        DemandeStatut demandeStatut = new DemandeStatut();
        demandeStatut.setDateStatut(LocalDateTime.now());
        demandeStatut.setDemande(demande);
        demandeStatut.setStatut(goodStatut);
        demandeStatutService.saveDemandeStatut(demandeStatut);
        

        if (demande == null) {
            return "redirect:/forage/demande?error=not_found";
        }
        return "redirect:/forage/devis/list/"+demandeId;
    }

    // @GetMapping("chiffre_affaire")
    // public String chiffreDaffaire(Model model) {
    //     Double chiffreAffaire = devisService.totalt();
    //     model.addAttribute("chiffre", chiffreAffaire);
    //     return "forage/devis_total/list";
    // }
}
