package com.example.forage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.forage.DAO.ClientDAO;
import com.example.forage.model.Client;
@Service
public class ClientService {
    @Autowired
    private ClientDAO clientDAO;

    public List<Client> getAllClients() {
        return clientDAO.findAll();
    }
    public Client getClientById(Long id) {
        return clientDAO.findById(id).orElse(null);
    }
    public Client saveClient(Client client) {
        return clientDAO.save(client);
    }
    public void deleteClient(Long id) {
        clientDAO.deleteById(id);
    }
    public void updateClient(Long id, Client clientDetails) {
        Client client = clientDAO.findById(id).orElse(null);
        if (client != null) {
            client.setNom(clientDetails.getNom());
            client.setContact(clientDetails.getContact());
            createClient(client);
        }
    }
    public void createClient(Client client) {
        clientDAO.save(client);
    }
}
