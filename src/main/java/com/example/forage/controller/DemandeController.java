package com.example.forage.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.*;
import com.example.forage.model.*;
import com.example.forage.service.ClientService;
import com.example.forage.service.DemandeService;
import com.example.forage.service.DemandeStatutService;
import com.example.forage.service.StatutService;

@Controller
@RequestMapping("/forage/demande")
public class DemandeController {
    @Autowired
    private DemandeService demandeService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private StatutService statutService;

    @Autowired
    private DemandeStatutService demandeStatutService;

    @GetMapping
    public String getAllDemandes(Model model) {
        List<Demande> demandes = demandeService.getAllDemandes();
        demandeService.sortAllStatutByDate(demandes);
        model.addAttribute("demandes", demandes);
        return "forage/demande/list";
    }

    @GetMapping("/form")
    public String showAddForm(Model model) {
        model.addAttribute("demande", new Demande());
        model.addAttribute("clients", clientService.getAllClients());
        return "forage/demande/form";
    }

    @PostMapping("/add")
    public String addDemande(@ModelAttribute("demande") Demande demande) {
        demande.setDateDemande(LocalDateTime.now());
        if (demande.getId() != null) {
            demandeService.updateDemande(demande.getId(), demande);
        } else {
            Demande saved = demandeService.saveDemande(demande);
            Statut statut = statutService.getStatutById(1L);
            DemandeStatut demandeStatut = new DemandeStatut();
                demandeStatut.setDateStatut(LocalDateTime.now());
                demandeStatut.setDemande(saved);
                demandeStatut.setStatut(statut);
            demandeStatutService.saveDemandeStatut(demandeStatut);
        }
        return "redirect:/forage/demande";
    }

    @GetMapping("/edit/{id}")
    public String editDemande(@PathVariable("id") Long id, Model model) {
        Demande demande = demandeService.getDemandeById(id);
        model.addAttribute("demande", demande);
        model.addAttribute("clients", clientService.getAllClients());
        return "forage/demande/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteDemande(@PathVariable("id") Long id) {
        demandeService.deleteDemande(id);
        return "redirect:/forage/demande";
    }

}
