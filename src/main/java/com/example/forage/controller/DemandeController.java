package com.example.forage.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.*;

import com.example.forage.DAO.ParametreDAO_forage;
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

    @Autowired 
    private ParametreDAO_forage parametreDAO;

    @GetMapping({"","/dclient/{clientId}","/dstatut/{statutId}"})
    public String getAllDemandes(@PathVariable(value = "clientId",required = false) Optional<Long> clientId,@PathVariable(value = "statutId",required = false) Optional<Long> statutId,Model model) {
        if (clientId.isPresent()) {
            List<Demande> demandes = demandeService.getDemandesByClientId(clientId.get());
            model.addAttribute("demandes", demandes);
            return "forage/demande/list";
        } else if (statutId.isPresent()) {
            List<Demande> demandes = demandeService.getDemandesByCurrentStatutId(statutId.get());
            model.addAttribute("demandes", demandes);
            return "forage/demande/list";
        } else {
            List<Demande> demandes = demandeService.getAllDemandes();
            model.addAttribute("demandes", demandes);
            return "forage/demande/list";
        }
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

    @GetMapping("/api/{id}")
    @ResponseBody
    public Map<String, Object> getDemandeApi(@PathVariable("id") Long id) {
        Demande demande = demandeService.getDemandeById(id);
        Parametre_forage parametre = parametreDAO.findAll().get(0);
        Map<String, Object> response = new HashMap<>();
        if (demande != null) {
            response.put("clientId", demande.getClient().getId());
            response.put("clientNom", demande.getClient().getNom());
            response.put("description", demande.getDescription());
            response.put("dateDemande", demande.getDateDemande().toString());
            response.put("district", demande.getDistrict());
            response.put("lieu", demande.getLieu());
            response.put("parametre", parametre.getNom());
            if (demande.getDemandeStatuts() != null && !demande.getDemandeStatuts().isEmpty()) {
                response.put("statut", demande.getDemandeStatuts().get(0).getStatut().getNom());
            } else {
                response.put("statut", "Non défini");
            }
        }
        return response;
    }

    @GetMapping("/statut/{id}")
    public String showStatutHistory(@PathVariable("id") Long id, 
                                    @RequestParam(value = "startDate", required = false) String startDateStr,
                                    @RequestParam(value = "endDate", required = false) String endDateStr,
                                    Model model) {
        Demande demande = demandeService.getDemandeById(id);
        if (demande != null) {
            List<DemandeStatut> filteredStatuts = null;
            if (startDateStr != null && !startDateStr.isEmpty() && endDateStr != null && !endDateStr.isEmpty()) {
                try {
                    LocalDateTime start = LocalDate.parse(startDateStr).atStartOfDay();
                    LocalDateTime end = LocalDate.parse(endDateStr).atTime(23, 59, 59);
                    filteredStatuts = demandeStatutService.getFilteredStatuts(id, start, end);
                    if (filteredStatuts != null) {
                        filteredStatuts.sort((s1, s2) -> s2.getDateStatut().compareTo(s1.getDateStatut()));
                    }
                } catch (Exception e) {
                    
                }
            }
            model.addAttribute("demande", demande);
            model.addAttribute("history", filteredStatuts != null ? filteredStatuts : demande.getDemandeStatuts());
            model.addAttribute("startDate", startDateStr);
            model.addAttribute("endDate", endDateStr);
            return "forage/demande/statut_list";
        }
        return "redirect:/forage/demande";
    }

    @GetMapping("/statut/form/{id}")
    public String showStatutForm(@PathVariable("id") Long id, Model model) {
        Demande demande = demandeService.getDemandeById(id);
        if (demande != null) {
            model.addAttribute("demande", demande);
            model.addAttribute("statuts", statutService.getAll());
            return "forage/demande/statut";
        }
        return "redirect:/forage/demande";
    }

    @PostMapping("/statut/update")
    public String updateStatut(@RequestParam("demandeId") Long demandeId, 
                               @RequestParam("statutId") Long statutId, 
                               @RequestParam("observation") String observation) {
        Demande demande = demandeService.getDemandeById(demandeId);
        Statut statut = statutService.getStatutById(statutId);
        
        if (demande != null && statut != null) {
            DemandeStatut demandeStatut = new DemandeStatut();
            demandeStatut.setDemande(demande);
            demandeStatut.setStatut(statut);
            demandeStatut.setObservation(observation);
            demandeStatut.setDateStatut(LocalDateTime.now());
            demandeStatutService.saveDemandeStatut(demandeStatut);
        }
        return "redirect:/forage/demande/statut/" + demandeId;
    }
    @PostMapping("/statut/edit")
    public String editStatut(@RequestParam("id") Long id,
                             @RequestParam("demandeId") Long demandeId,
                             @RequestParam("date") String dateStr,
                             @RequestParam("observation") String observation) {
        DemandeStatut demandeStatut = demandeStatutService.findById(id);
        if (demandeStatut != null) {
            try {
                LocalDateTime date = LocalDateTime.parse(dateStr);
                demandeStatut.setDateStatut(date);
                demandeStatut.setObservation(observation);
                demandeStatutService.saveDemandeStatut(demandeStatut);
            } catch (Exception e) {
               
            }
        }
        return "redirect:/forage/demande/statut/" + demandeId;
    }

}
