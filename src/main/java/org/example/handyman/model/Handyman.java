package org.example.handyman.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Handyman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private float rating;
    private String skill;
    private String imageName;
    private String imageType;
    @Lob
    private byte[] image;
    @ManyToMany(mappedBy = "favoriteHandymen")
    private Set<Client> favoritedByClients;
}
