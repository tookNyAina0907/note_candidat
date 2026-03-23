package com.example.note.controller;

import com.example.note.service.*;
import com.example.note.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/note/candidats")
public class CandidatController {

    @Autowired
    private CandidatService candidatService;

    @GetMapping
    public String listCandidats(Model model) {
        List<Candidat> candidats = candidatService.getAllCandidats();
        model.addAttribute("candidats", candidats);
        return "note/candidat/list";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("candidat", new Candidat());
        return "note/candidat/form";
    }

    @PostMapping("/save")
    public String saveCandidat(@ModelAttribute("candidat") Candidat candidat) {
        candidatService.saveCandidat(candidat);
        return "redirect:/note/candidats";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Candidat candidat = candidatService.getCandidatById(id);
        if (candidat == null) {
            return "redirect:/note/candidats";
        }
        model.addAttribute("candidat", candidat);
        return "note/candidat/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteCandidat(@PathVariable("id") Integer id) {
        candidatService.deleteCandidatById(id);
        return "redirect:/note/candidats";
    }
}
