package com.example.forage.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.forage.model.DemandeStatut;
import com.example.forage.model.Statut;
import com.example.forage.service.ClientService;
import com.example.forage.service.DemandeService;
import com.example.forage.service.DemandeStatutService;
import com.example.forage.service.DevisService;
import com.example.forage.service.StatutService;
import com.example.forage.service.TypeDevisService;

@Controller
@RequestMapping("/forage/dashboard")
public class DashboardController {
    @Autowired
    private ClientService clientService;

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

    @GetMapping
    public String dashboard(Model model) {
        Double chiffreAffaire = devisService.totalt();
        model.addAttribute("chiffre", chiffreAffaire);
        long totalClients = clientService.getAllClients().size();
        long totalDevis = devisService.getAllDevis().size();
        long totalDemandes = demandeService.getAllDemandes().size();
        model.addAttribute("totalClients", totalClients);
        model.addAttribute("totalDevis", totalDevis);
        model.addAttribute("totalDemandes", totalDemandes);
        List<Statut> statuts = statutService.getAll();
        model.addAttribute("statuts", statuts);
        for (Statut statut : statuts) {
            Long count = demandeStatutService.countByStatutId(statut.getId());
            model.addAttribute(statut.getNom(), count);
        }
        return "forage/devis_total/list";
    }
    @GetMapping( {"/statut","/statut/{statutId}"})
    public String dashboardStatut(@PathVariable(value = "statutId", required = false) Long statutId, Model model) {
        // List<Statut> statuts = statutService.getAll();
        List<DemandeStatut> demandesStatut = new ArrayList<>();
        // model.addAttribute("statuts", statuts);
        if (statutId != null) {
            demandesStatut = demandeStatutService.findByStatutId(statutId);
        }else {
            demandesStatut = demandeStatutService.getFilteredStatuts(null, null, null);
        }
        model.addAttribute("demandesStatut", demandesStatut);

        return "forage/devis_total/statutlist";
    }


}
