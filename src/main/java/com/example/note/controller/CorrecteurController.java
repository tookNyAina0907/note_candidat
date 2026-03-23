package com.example.note.controller;

import com.example.note.service.*;
import com.example.note.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/note/correcteurs")
public class CorrecteurController {

    @Autowired
    private CorrecteurService correcteurService;

    @GetMapping
    public String listCorrecteurs(Model model) {
        List<Correcteur> correcteurs = correcteurService.getAllCorrecteurs();
        model.addAttribute("correcteurs", correcteurs);
        return "note/correcteur/list";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("correcteur", new Correcteur());
        return "note/correcteur/form";
    }

    @PostMapping("/save")
    public String saveCorrecteur(@ModelAttribute("correcteur") Correcteur correcteur) {
        correcteurService.saveCorrecteur(correcteur);
        return "redirect:/note/correcteurs";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Correcteur correcteur = correcteurService.getCorrecteurById(id);
        if (correcteur == null) {
            return "redirect:/note/correcteurs";
        }
        model.addAttribute("correcteur", correcteur);
        return "note/correcteur/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteCorrecteur(@PathVariable("id") Integer id) {
        correcteurService.deleteCorrecteurById(id);
        return "redirect:/note/correcteurs";
    }
}
