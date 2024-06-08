package com.osmel.centredentaire.services.impls;


import com.osmel.centredentaire.entities.AntecendentMedical;
import com.osmel.centredentaire.entities.Dentiste;
import com.osmel.centredentaire.entities.DossierMedical;
import com.osmel.centredentaire.entities.Patient;
import com.osmel.centredentaire.enums.CategorieAntecedentsMedicaux;
import com.osmel.centredentaire.repositories.AntecedantMedRepository;
import com.osmel.centredentaire.repositories.DentisteRepo;
import com.osmel.centredentaire.repositories.DossierMedicalRepo;
import com.osmel.centredentaire.repositories.PatientRepo;
import com.osmel.centredentaire.services.interfaces.PatientService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.UUID;

@Service

public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientRepo patientRepo;
    @Autowired
    DentisteRepo dentisteRepo;
    @Autowired
    DossierMedicalRepo dossierMedicalRepo;
    @Autowired
    AntecedantMedRepository antecendentMedicalRepo;

    @Transactional
    public Patient ajouter(Patient patient){
        patientRepo.save(patient);
        return patient;
    }



    @Override
    public void delete(Long id) {
        patientRepo.deleteById(id);
    }

    @Override
    public Patient findById(Long id) {
        return patientRepo.findById(id).orElse(null);
    }


    }

