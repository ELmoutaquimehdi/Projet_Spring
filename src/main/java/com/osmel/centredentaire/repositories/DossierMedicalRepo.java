package com.osmel.centredentaire.repositories;

import com.osmel.centredentaire.entities.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DossierMedicalRepo extends JpaRepository<DossierMedical, String> {

    List<DossierMedical> findByMedecinTraitant_Id(Long id);

    DossierMedical findByNumeroDossier(String numero);

    List<DossierMedical> findAllByMedecinTraitant_Nom(String nomDentiste);
}
