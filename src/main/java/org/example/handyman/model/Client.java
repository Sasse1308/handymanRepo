package org.example.handyman.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long client_id;
    private String firstName;
    private String lastName;
    @Column(nullable = true)
    private String email;
    private String phone;
    private String address;
    @ManyToMany
    @JoinTable(
            name = "favorite_handymen",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private Set<Handyman> favoriteHandymen;
}
