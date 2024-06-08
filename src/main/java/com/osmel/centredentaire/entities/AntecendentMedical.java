package com.osmel.centredentaire.entities;

import com.osmel.centredentaire.enums.CategorieAntecedentsMedicaux;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AntecendentMedical {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(mappedBy = "antecendentMedicals")
    private List<Patient> patientsAvecCeAntecendentMedicale ;

    private String libelle;
    @Enumerated(value = EnumType.STRING)
    private CategorieAntecedentsMedicaux categorie;
}
