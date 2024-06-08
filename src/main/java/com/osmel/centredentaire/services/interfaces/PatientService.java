package com.osmel.centredentaire.services.interfaces;


import com.osmel.centredentaire.entities.Patient;

public interface PatientService {

    Patient ajouter(Patient patient);

        // Other methods...
        void delete(Long id);
        Patient findById(Long id);



}
