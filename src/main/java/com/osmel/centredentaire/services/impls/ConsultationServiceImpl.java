package com.osmel.centredentaire.services.impls;

import com.osmel.centredentaire.entities.Consultation;
import com.osmel.centredentaire.repositories.ConsultationRepo;
import com.osmel.centredentaire.services.interfaces.ConsultationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConsultationServiceImpl implements ConsultationService {
    ConsultationRepo consultationRepo;
    @Override
    public Consultation ajouter(Consultation consultation) {
        return consultationRepo.save(consultation);
    }

    @Override
    public Consultation trouve(Long consultationId) {
        return consultationRepo.findById(consultationId).orElse(null);
    }

    @Override
    public void supprimer(Long consultationId) {
         consultationRepo.deleteById(consultationId);
    }
}
