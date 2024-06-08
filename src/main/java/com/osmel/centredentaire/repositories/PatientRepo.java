package com.osmel.centredentaire.repositories;

import com.osmel.centredentaire.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient, Long> {
}
