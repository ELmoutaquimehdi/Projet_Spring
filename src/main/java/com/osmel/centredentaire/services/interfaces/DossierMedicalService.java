package com.osmel.centredentaire.services.interfaces;

import com.osmel.centredentaire.entities.DossierMedical;

import java.util.List;

public interface DossierMedicalService {

    public List<DossierMedical> findAllByDentiste(String nomDentiste);
    public List<DossierMedical> findAll();

    DossierMedical findByNumeroDossier(String numeroDossier);
}
