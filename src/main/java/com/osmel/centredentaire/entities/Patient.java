package com.osmel.centredentaire.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.osmel.centredentaire.enums.GroupeSanguin;
import com.osmel.centredentaire.enums.Mutuelle;
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
public class Patient extends Personne{
    private LocalDate dateNaissance;
    private Mutuelle mutuelle;
    private GroupeSanguin groupeSanguin;
    private String profession;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany
    private List<AntecendentMedical> antecendentMedicals;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    private DossierMedical dossierMedical;
}
