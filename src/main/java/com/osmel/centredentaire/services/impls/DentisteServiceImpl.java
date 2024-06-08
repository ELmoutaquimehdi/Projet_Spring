package com.osmel.centredentaire.services.impls;


import com.osmel.centredentaire.entities.Dentiste;
import com.osmel.centredentaire.entities.Patient;
import com.osmel.centredentaire.repositories.DentisteRepo;
import com.osmel.centredentaire.repositories.PatientRepo;
import com.osmel.centredentaire.services.interfaces.DentisteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class DentisteServiceImpl implements DentisteService {

    PatientRepo patientRepo;
    DentisteRepo dentisteRepo;


    @Override
    public List<Patient> findPatientsByDentisteId(Long id) {
        return null;
    }

    @Override
    public Dentiste getInformationsDentisteConnecte() {
        return null;
    }

    public Dentiste trouve(){
        Dentiste d;
        d=dentisteRepo.findDentisteByNomUtilisateur("ELMEHDI");
        return d;
    }

    @Override
    public Dentiste find() {
        return null;
    }


}
