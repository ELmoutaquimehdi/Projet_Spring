package com.osmel.centredentaire.repositories;

import com.osmel.centredentaire.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepo extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByNomUtilisateur(String username);
}
