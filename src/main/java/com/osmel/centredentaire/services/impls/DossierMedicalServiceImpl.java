package com.osmel.centredentaire.services.impls;

import com.osmel.centredentaire.entities.DossierMedical;
import com.osmel.centredentaire.repositories.DossierMedicalRepo;
import com.osmel.centredentaire.services.interfaces.DossierMedicalService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DossierMedicalServiceImpl implements DossierMedicalService {



    private final DossierMedicalRepo dossierMedicalRepository;




    public List<DossierMedical> findAll() {

        // Implémentation pour trouver tous les dossiers médicaux par le nom du dentiste
        return dossierMedicalRepository.findAll();
    }

    @Override
    public DossierMedical findByNumeroDossier(String numeroDossier) {
        return dossierMedicalRepository.findByNumeroDossier(numeroDossier);
    }





    @Override
    public List<DossierMedical> findAllByDentiste(String nomDentiste)
    {
        return null;
    }
}
