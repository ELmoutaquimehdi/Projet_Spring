package com.osmel.centredentaire.entities;

import com.osmel.centredentaire.enums.TypeConsultation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsultation;
    private LocalDate dateConsultation;
    @Enumerated(value = EnumType.STRING)
    private TypeConsultation typeConsultation;
    @OneToMany(mappedBy = "consultation")
    private List<InterventionMedecin> interventions;
    @ManyToOne
    private DossierMedical dossierMedical;

}
