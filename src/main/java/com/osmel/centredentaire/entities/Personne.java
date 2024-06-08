package com.osmel.centredentaire.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity

@Inheritance(strategy = InheritanceType.JOINED)
@Data @NoArgsConstructor @AllArgsConstructor
public abstract class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String adresse;
    private String email;
    private String telephone;
    private String prenom;
    private String cin;
    private String sexe;
}
