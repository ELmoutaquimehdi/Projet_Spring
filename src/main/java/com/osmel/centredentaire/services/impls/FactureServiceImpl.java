package com.osmel.centredentaire.services.impls;

import com.osmel.centredentaire.entities.Facture;
import com.osmel.centredentaire.repositories.FactureRepo;
import com.osmel.centredentaire.services.interfaces.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactureServiceImpl implements FactureService {


    private final FactureRepo factureRepo;

    @Autowired  // Ensure proper injection
    public FactureServiceImpl(FactureRepo factureRepo) {
        this.factureRepo = factureRepo;
    }

    @Override
    public Facture trouveparid(Long id) {

        return factureRepo.findById(id).orElse(null);
    }
}
