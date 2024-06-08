package com.osmel.centredentaire.repositories;

import com.osmel.centredentaire.entities.InterventionMedecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterventionMedecinRepo extends JpaRepository<InterventionMedecin,Long> {
}
