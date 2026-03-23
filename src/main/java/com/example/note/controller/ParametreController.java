package com.example.note.controller;

import com.example.note.service.*;
import com.example.note.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/note/parametres")
public class ParametreController {

    @Autowired
    private ParametreService parametreService;

    @Autowired
    private MatiereService matiereService;
    
    @Autowired
    private ResolutionService resolutionService;
    
    @Autowired
    private OperateurService operateurService;

    @GetMapping
    public String listParametres(Model model) {
        List<Parametre> parametres = parametreService.getAllParametres();
        model.addAttribute("parametres", parametres);
        return "note/parametre/list";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("parametre", new Parametre());
        model.addAttribute("matieres", matiereService.getAllMatieres());
        model.addAttribute("resolutions", resolutionService.getAllResolutions());
        model.addAttribute("operateurs", operateurService.getAllOperateurs());
        return "note/parametre/form";
    }

    @PostMapping("/save")
    public String saveParametre(@ModelAttribute("parametre") Parametre parametre) {
        parametreService.saveParametre(parametre);
        return "redirect:/note/parametres";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Parametre parametre = parametreService.getParametreById(id);
        if (parametre == null) {
            return "redirect:/note/parametres";
        }
        model.addAttribute("parametre", parametre);
        model.addAttribute("matieres", matiereService.getAllMatieres());
        model.addAttribute("resolutions", resolutionService.getAllResolutions());
        model.addAttribute("operateurs", operateurService.getAllOperateurs());
        return "note/parametre/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteParametre(@PathVariable("id") Integer id) {
        parametreService.deleteParametreById(id);
        return "redirect:/note/parametres";
    }
}
