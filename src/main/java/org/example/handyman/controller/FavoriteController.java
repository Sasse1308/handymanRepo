package org.example.handyman.controller;

import org.example.handyman.model.Handyman;
import org.example.handyman.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/{clientId}/add/{handymanId}")
    public ResponseEntity<String> addFavorite(@PathVariable Long clientId, @PathVariable Long handymanId) {
        favoriteService.addFavoriteHandyman(clientId, handymanId);
        return ResponseEntity.ok("Handyman added to favorites!");
    }
    @GetMapping("/{clientId}/list")
    public ResponseEntity<Set<Handyman>> getFavoriteHandymen(@PathVariable Long clientId) {
        try {
            Set<Handyman> favorites = favoriteService.getFavoriteHandymen(clientId);
            if (favorites.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(favorites); // Return 204 No Content if no favorites found
            }
            return ResponseEntity.ok(favorites);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Set.of()); // Return an empty set in the response body
        }
    }
    @DeleteMapping("/{clientId}/remove/{handymanId}")
    public ResponseEntity<String> removeFavorite(@PathVariable Long clientId, @PathVariable Long handymanId) {
        try {
            favoriteService.removeFavoriteHandyman(clientId, handymanId);
            return ResponseEntity.ok("Handyman removed from favorites!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
