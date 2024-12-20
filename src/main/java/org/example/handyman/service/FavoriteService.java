package org.example.handyman.service;

import jakarta.transaction.Transactional;
import org.example.handyman.model.Client;
import org.example.handyman.model.Handyman;
import org.example.handyman.repository.ClientRepo;
import org.example.handyman.repository.HandyManRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FavoriteService {
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private HandyManRepo handyManRepo;
    public void addFavoriteHandyman(Long clientId, Long handymanId) {
        // Retrieve the client
        Client client = clientRepo.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        // Retrieve the handyman
        Handyman handyman = handyManRepo.findById(handymanId)
                .orElseThrow(() -> new RuntimeException("Handyman not found"));

        // Add the handyman to the client's favorites
        client.getFavoriteHandymen().add(handyman);

        // Save the client back to persist the change
        clientRepo.save(client);
    }
    public Set<Handyman> getFavoriteHandymen(Long clientId) {
        return clientRepo.findFavoriteHandymenByClientId(clientId);
    }
    @Transactional
    public void removeFavoriteHandyman(Long clientId, Long handymanId) {
        // Delete the specific favorite handyman entry for the given client and handyman
        clientRepo.deleteFavoriteHandyman(clientId, handymanId);
    }


}
