package com.osmel.centredentaire.services.interfaces;


import com.osmel.centredentaire.entities.Dentiste;
import com.osmel.centredentaire.entities.Patient;

import java.util.List;

public interface DentisteService {
    List<Patient> findPatientsByDentisteId(Long id);
    Dentiste getInformationsDentisteConnecte();


    Dentiste trouve();
    Dentiste find();
}
