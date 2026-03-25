package com.example.forage.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        if (libelles != null && prixs != null && qtes != null && 
            libelles.length == prixs.length && libelles.length == qtes.length) {
            for (int i = 0; i < libelles.length; i++) {
                DetailDevis detail = new DetailDevis();
                detail.setLibelle(libelles[i]);
                detail.setPrix(prixs[i]);
                detail.setQuantite(qtes[i]);
                detail.setDevis(devis);
                details.add(detail);
            }
        }
        devis.setDetailDevis(details);
        Long id = demande.getDemandeStatuts().get(0).getId() + 1L;
        devisService.saveDevis(devis);
        DemandeStatut demandeStatut = new DemandeStatut();
        demandeStatut.setDateStatut(LocalDateTime.now());
        demandeStatut.setDemande(demande);
        demandeStatut.setStatut(statutService.getStatutById(id));
        demandeStatutService.saveDemandeStatut(demandeStatut);
        
        return "redirect:/forage/demande"; // Redirect to demande list for now
    }

    @GetMapping("/list/{demandeId}")
    public String listDevisByDemande(@PathVariable("demandeId") Long demandeId, Model model) {
        Demande demande = demandeService.getDemandeById(demandeId);
        if (demande == null) {
            return "redirect:/forage/demande?error=not_found";
        }
        
        List<Devis> devisList = devisService.getDevisByDemande(demande);
        model.addAttribute("demande", demande);
        model.addAttribute("devisList", devisList);
        return "forage/devis/list";
    }
}
