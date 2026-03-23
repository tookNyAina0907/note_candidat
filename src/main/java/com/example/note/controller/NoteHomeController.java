package com.example.note.controller;

import com.example.note.service.*;
import com.example.note.model.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoteHomeController {

    @GetMapping("/note")
    public String home() {
        return "note/home";
    }
}
