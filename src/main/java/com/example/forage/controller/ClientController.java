package com.example.forage.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.*;
import com.example.forage.model.Client;
import com.example.forage.service.ClientService;

@Controller
@RequestMapping("/forage/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public String getAllClients(Model model) {
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
        return "forage/client/list";
    }

    @GetMapping("/form")
    public String showAddForm(Model model) {
        model.addAttribute("client", new Client());
        return "forage/client/form";
    }

    @PostMapping("/add")
    public String addClient(@ModelAttribute("client") Client client) {
        if (client.getId() != null) {
            clientService.updateClient(client.getId(), client);
        } else {
            clientService.saveClient(client);
        }
        return "redirect:/forage/client";
    }

    @GetMapping("/edit/{id}")
    public String editClient(@PathVariable("id") Long id, Model model) {
        Client client = clientService.getClientById(id);
        model.addAttribute("client", client);
        return "forage/client/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return "redirect:/forage/client";
    }

}
