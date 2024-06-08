package com.osmel.centredentaire;

import com.osmel.centredentaire.entities.Dentiste;
import com.osmel.centredentaire.entities.DossierMedical;
import com.osmel.centredentaire.entities.Patient;
import com.osmel.centredentaire.enums.Disponibilite;
import com.osmel.centredentaire.enums.GroupeSanguin;
import com.osmel.centredentaire.enums.Mutuelle;
import com.osmel.centredentaire.enums.StatutPaiement;
import com.osmel.centredentaire.repositories.DentisteRepo;
import com.osmel.centredentaire.repositories.DossierMedicalRepo;
import com.osmel.centredentaire.repositories.PatientRepo;
import com.osmel.centredentaire.services.interfaces.DentisteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class CentreDentaireApplication {

    public static void main(String[] args) {
        SpringApplication.run(CentreDentaireApplication.class, args);
    }

    /*
    @Autowired
    private DentisteRepo dentisteRepo;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            // Create and populate the disponibilites map
            Map<DayOfWeek, Disponibilite> disponibilites = new HashMap<>();
            disponibilites.put(DayOfWeek.MONDAY, Disponibilite.MONDAY);
            disponibilites.put(DayOfWeek.TUESDAY, Disponibilite.TUESDAY);

            // Find the Dentiste by username (assuming 'ELMEHDI' is a username)
            Dentiste dentiste = dentisteRepo.findDentisteByNomUtilisateur("ELMEHDI");

            if (dentiste != null) {
                // Set disponibilites and save Dentiste
                dentiste.setDisponibilites(disponibilites);
                dentiste.setDossierMedicalList(null); // Assuming this is part of Dentiste properties
                dentisteRepo.save(dentiste);
                System.out.println("Saved Dentiste: " + dentiste);
            } else {
                System.out.println("Dentiste with username 'ELMEHDI' not found.");
            }
        };
    }*/



/*
    @Bean
    public CommandLineRunner demo(PatientRepo patientRepository, DossierMedicalRepo dossierMedicalRepository) {
        return (args) -> {
            // Création des dossiers médicaux d'abord
            DossierMedical dossier1 = new DossierMedical();
            dossier1.setNumeroDossier("D1");
            dossier1.setDateCreation(LocalDate.now());
            dossier1.setStatutPaiement(StatutPaiement.PAYE);

            DossierMedical dossier2 = new DossierMedical();
            dossier2.setNumeroDossier("D2");
            dossier2.setDateCreation(LocalDate.now());
            dossier2.setStatutPaiement(StatutPaiement.EN_ATTENTE);

            DossierMedical dossier3 = new DossierMedical();
            dossier3.setNumeroDossier("D3");
            dossier3.setDateCreation(LocalDate.now());
            dossier3.setStatutPaiement(StatutPaiement.NON_PAYE);

            // Sauvegarder d'abord les dossiers médicaux
            dossierMedicalRepository.saveAll(Arrays.asList(dossier1, dossier2, dossier3));

            // Création des patients après sauvegarde des dossiers médicaux
            Patient patient1 = new Patient();
            patient1.setDateNaissance(LocalDate.of(1990, 1, 1));
            patient1.setMutuelle(Mutuelle.CIMR);
            patient1.setGroupeSanguin(GroupeSanguin.O_POSITIF);
            patient1.setProfession("Dentiste");
            patient1.setDossierMedical(dossier1);  // Associer patient1 à dossier1

            Patient patient2 = new Patient();
            patient2.setDateNaissance(LocalDate.of(1985, 5, 15));
            patient2.setMutuelle(Mutuelle.CNSS);
            patient2.setGroupeSanguin(GroupeSanguin.A_NEGATIF);
            patient2.setProfession("Chirurgien");
            patient2.setDossierMedical(dossier2);  // Associer patient2 à dossier2

            Patient patient3 = new Patient();
            patient3.setDateNaissance(LocalDate.of(2000, 10, 20));
            patient3.setMutuelle(Mutuelle.CIMR);
            patient3.setGroupeSanguin(GroupeSanguin.B_POSITIF);
            patient3.setProfession("Infirmière");
            patient3.setDossierMedical(dossier3);  // Associer patient3 à dossier3

            // Sauvegarder les patients maintenant
            patientRepository.saveAll(Arrays.asList(patient1, patient2, patient3));
        };
    }





*/

    /*
    @Bean
    public CommandLineRunner demo(PatientRepo patientRepository, DossierMedicalRepo dossierMedicalRepository) {
        return (args) -> {
            // Récupérer tous les patients depuis la base de données
            List<Patient> patients = patientRepository.findAll();

            System.out.println("Informations des Patients :");
            System.out.println("==============================");

            // Parcourir la liste des patients et afficher leurs informations
            for (Patient patient : patients) {
                System.out.println("Patient:");
                System.out.println("ID: " + patient.getId());
                System.out.println("Nom: " + patient.getNom());
                System.out.println("Date de naissance: " + patient.getDateNaissance());
                System.out.println("Mutuelle: " + patient.getMutuelle());
                System.out.println("Groupe sanguin: " + patient.getGroupeSanguin());
                System.out.println("Profession: " + patient.getProfession());
                System.out.println("cin"+patient.getCin());
                System.out.println("email"+patient.getEmail());
                System.out.println("telephone"+patient.getTelephone());

                // Afficher les informations du dossier médical associé, s'il existe
                DossierMedical dossierMedical = patient.getDossierMedical();
                if (dossierMedical != null) {
                    System.out.println("Dossier Médical:");
                    System.out.println("   Numéro de dossier: " + dossierMedical.getNumeroDossier());
                    System.out.println("   Date de création: " + dossierMedical.getDateCreation());
                    System.out.println("   Statut de paiement: " + dossierMedical.getStatutPaiement());
                } else {
                    System.out.println("Ce patient n'a pas de dossier médical.");
                }

                System.out.println("==============================");
            }
        };
    }



*/
    /*
 @Bean
    public CommandLineRunner demo(DentisteRepo dentisteRepo,PatientRepo patientRepository, DossierMedicalRepo dossierMedicalRepository) {
        return (args) -> {

            Dentiste d=dentisteRepo.findDentisteByNomUtilisateur("ELMEHDI");
            List<DossierMedical> dossierMedicals=dossierMedicalRepository.findAll();
            for(DossierMedical dossierMedical:dossierMedicals){
                System.out.println(dossierMedical.getStatutPaiement());
                System.out.println(dossierMedical.getNumeroDossier());
                System.out.println(d.getNomUtilisateur());
                dossierMedical.setMedecinTraitant(d);
                dossierMedicalRepository.save(dossierMedical);
            }









        };}

*/














    /*











*/












}
