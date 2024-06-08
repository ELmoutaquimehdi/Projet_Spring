package com.osmel.centredentaire.repositories;

import com.osmel.centredentaire.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepo extends JpaRepository<Consultation,Long> {
}
