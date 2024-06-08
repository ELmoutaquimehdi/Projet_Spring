package com.osmel.centredentaire.entities;

import com.osmel.centredentaire.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class  Utilisateur extends Personne {
    private String motDepass;
    @Column(unique = true)
    private String nomUtilisateur;
    private Role role;

}
