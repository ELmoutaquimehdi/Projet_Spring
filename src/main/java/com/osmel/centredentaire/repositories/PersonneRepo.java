package com.osmel.centredentaire.repositories;

import com.osmel.centredentaire.entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonneRepo extends JpaRepository<Personne ,Long> {

}
