package com.osmel.centredentaire.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.osmel.centredentaire.enums.Assurance;
import com.osmel.centredentaire.enums.Disponibilite;
import com.osmel.centredentaire.enums.Specialite;
import com.osmel.centredentaire.enums.StatusEmploye;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dentiste extends Utilisateur{
    private LocalDate dateRetourConge;
    private Double salaireDeBase;
    @Enumerated(value = EnumType.STRING)
    private Specialite specialite;
    @Enumerated(value = EnumType.STRING)
    private Assurance assurance;
    @Enumerated(value = EnumType.STRING)
    private StatusEmploye statusActuel;
    @ElementCollection
    @CollectionTable(name = "dentiste_disponibilites",joinColumns = @JoinColumn(name = "dentiste_id"))
    @MapKeyEnumerated(EnumType.STRING)
    private Map<DayOfWeek, Disponibilite> disponibilites;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "medecinTraitant")
    private List<DossierMedical> dossierMedicalList;

}
