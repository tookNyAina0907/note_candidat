package com.example.forage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForageHomeController {
    @GetMapping("/forage")
    public String home() {
        return "forage/home";
    }
}
