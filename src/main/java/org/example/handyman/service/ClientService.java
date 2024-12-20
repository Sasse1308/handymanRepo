package org.example.handyman.service;

import org.example.handyman.model.Client;
import org.example.handyman.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepo clientRepo;
    public Client save(Client client){
        clientRepo.save(client);
        return client;
    }
    public List<Client> findAll(){
        return clientRepo.findAll();
    }
}
