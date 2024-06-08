package com.osmel.centredentaire.services.interfaces;

import com.osmel.centredentaire.entities.Consultation;

public interface ConsultationService {
    Consultation ajouter(Consultation consultation);
    Consultation trouve ( Long consultationId);
    void supprimer( Long consultationId);



}
