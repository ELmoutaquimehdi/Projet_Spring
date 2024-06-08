package com.osmel.centredentaire.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.osmel.centredentaire.enums.StatutPaiement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DossierMedical {
    @Id
    private String numeroDossier;
    private LocalDate dateCreation;
    private StatutPaiement statutPaiement;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "dossierMedical")
    private List<Consultation> consultations;
    @OneToOne(mappedBy = "dossierMedical" ,cascade = CascadeType.ALL)
    private Patient patient;
    @ManyToOne
    private Dentiste medecinTraitant;
    @OneToMany(mappedBy = "dossierMedical")
    private List<Facture> factures ;

}
