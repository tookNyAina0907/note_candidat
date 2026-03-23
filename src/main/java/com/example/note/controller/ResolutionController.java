package com.example.note.controller;

import com.example.note.service.*;
import com.example.note.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/resolutions")
public class ResolutionController {

    @Autowired
    private ResolutionService resolutionService;

    @GetMapping
    public String listResolutions(Model model) {
        List<Resolution> resolutions = resolutionService.getAllResolutions();
        model.addAttribute("resolutions", resolutions);
        return "note/resolution/list";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("resolution", new Resolution());
        return "note/resolution/form";
    }

    @PostMapping("/save")
    public String saveResolution(@ModelAttribute("resolution") Resolution resolution) {
        resolutionService.saveResolution(resolution);
        return "redirect:/resolutions";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Resolution resolution = resolutionService.getResolutionById(id);
        if (resolution == null) {
            return "redirect:/resolutions";
        }
        model.addAttribute("resolution", resolution);
        return "note/resolution/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteResolution(@PathVariable("id") Integer id) {
        resolutionService.deleteResolutionById(id);
        return "redirect:/resolutions";
    }
}
