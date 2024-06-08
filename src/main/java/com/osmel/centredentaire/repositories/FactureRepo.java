package com.osmel.centredentaire.repositories;

import com.osmel.centredentaire.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepo extends JpaRepository<Facture , Long> {
}
