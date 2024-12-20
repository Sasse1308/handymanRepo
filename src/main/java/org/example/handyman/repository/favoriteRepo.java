package org.example.handyman.repository;

import org.example.handyman.model.Client;
import org.example.handyman.model.Handyman;
import org.springframework.data.jpa.repository.JpaRepository;

public interface favoriteRepo extends JpaRepository<Client,Long> {
}
