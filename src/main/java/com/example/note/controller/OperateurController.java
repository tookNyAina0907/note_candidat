package com.example.note.controller;

import com.example.note.service.*;
import com.example.note.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/operateurs")
public class OperateurController {

    @Autowired
    private OperateurService operateurService;

    @GetMapping
    public String listOperateurs(Model model) {
        List<Operateur> operateurs = operateurService.getAllOperateurs();
        model.addAttribute("operateurs", operateurs);
        return "note/operateur/list";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("operateur", new Operateur());
        return "note/operateur/form";
    }

    @PostMapping("/save")
    public String saveOperateur(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam("operateur") String operateur) {
        if (id!=null) {
            Operateur operateur1 = operateurService.getOperateurById(id);
            operateur1.setOperateur(operateur);
            operateurService.saveOperateur(operateur1);
            // operateurService.upateOperateur(new Operateur(id, operateur));
        }else{
            operateurService.saveOperateur(new Operateur(operateur));
        }
        return "redirect:/operateurs";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Operateur operateur = operateurService.getOperateurById(id);
        if (operateur == null) {
            return "redirect:/operateurs";
        }
        model.addAttribute("operateur", operateur);
        return "note/operateur/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteOperateur(@PathVariable("id") Integer id) {
        operateurService.deleteOperateurById(id);
        return "redirect:/operateurs";
    }
}
