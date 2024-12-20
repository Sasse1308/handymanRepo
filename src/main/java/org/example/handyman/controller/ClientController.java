package org.example.handyman.controller;

import org.example.handyman.model.Client;
import org.example.handyman.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Client")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client savedClient = clientService.save(client);
        return ResponseEntity.ok(savedClient);
    }
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.findAll();
    }
}
