package com.osmel.centredentaire.repositories;

import com.osmel.centredentaire.entities.Dentiste;
import com.osmel.centredentaire.entities.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DentisteRepo extends JpaRepository<Dentiste ,Long> {
    Dentiste findDentisteByNomUtilisateur(String nomUtilisateur);
}
