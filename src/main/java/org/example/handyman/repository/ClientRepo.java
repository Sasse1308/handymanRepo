package org.example.handyman.repository;

import org.example.handyman.model.Client;
import org.example.handyman.model.Handyman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ClientRepo extends JpaRepository<Client, Long> {
    @Query("SELECT c.favoriteHandymen FROM Client c WHERE c.client_id = :clientId")
    Set<Handyman> findFavoriteHandymenByClientId(Long clientId);

    @Modifying
    @Query(value = "DELETE FROM favorite_handymen WHERE client_id = :clientId AND id = :handymanId", nativeQuery = true)
    void deleteFavoriteHandyman(@Param("clientId") Long clientId, @Param("handymanId") Long handymanId);
}
