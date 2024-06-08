package com.osmel.centredentaire.web;

import com.osmel.centredentaire.entities.*;
import com.osmel.centredentaire.repositories.DossierMedicalRepo;
import com.osmel.centredentaire.repositories.FactureRepo;
import com.osmel.centredentaire.services.interfaces.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
public class Controller {

    private final DossierMedicalRepo dossierMedicalRepo;
    private final ConsultationService consultationService;
    private final DossierMedicalService dossierMedicalService;
    private final PatientService patientService;
    private final DentisteService dentisteService;
    private final FactureService factureService;

    private final FactureRepo factureRepo;
    private boolean authenticated = false; // Variable pour suivre l'état d'authentification

    public Controller(DossierMedicalRepo dossierMedicalRepo, ConsultationService consultationService, DossierMedicalService dossierMedicalService, PatientService patientService, DentisteService dentisteService, FactureService factureService, FactureRepo factureRepo) {
        this.dossierMedicalRepo = dossierMedicalRepo;
        this.consultationService = consultationService;
        this.dossierMedicalService = dossierMedicalService;
        this.patientService = patientService;
        this.dentisteService = dentisteService;
        this.factureService = factureService;
        this.factureRepo = factureRepo;
    }

    @GetMapping("/index")
    public String index(Model model) {
        // Vérifie si l'utilisateur est authentifié
        if (authenticated) {
            model.addAttribute("utilisateur", new Dentiste());
            return "index"; // Retourne la vue index si l'utilisateur est authentifié
        } else {
            return "redirect:/login"; // Redirection vers la page de login si l'utilisateur n'est pas authentifié
        }
    }




    // Méthode pour afficher le formulaire de login
    @GetMapping("/login")
    public String afficherFormulaireAuthentification(Model model) {
        model.addAttribute("dentiste", new Dentiste());
        return "login"; // Retourne le nom de la vue à afficher (login.html)
    }

    // Méthode pour traiter la soumission du formulaire de login
    @PostMapping("/login")
    public String authentifierUtilisateur(@ModelAttribute("dentiste") Dentiste dentiste) {
        Dentiste dentisteTrouve = dentisteService.trouve();
        if (dentisteTrouve != null &&
                dentisteTrouve.getNomUtilisateur().equals(dentiste.getNomUtilisateur()) &&
                dentisteTrouve.getMotDepass().equals(dentiste.getMotDepass())) {
            authenticated = true;
            return "redirect:/dentiste";
        } else {
            authenticated = false;
            return "redirect:/login?error"; // Ajouter un paramètre d'erreur en cas d'échec de l'authentification
        }
    }

    @GetMapping("/dentiste")
    public String getDentiste(Model model) {
        if (authenticated) {
            Dentiste dentiste = dentisteService.trouve();
            if (dentiste != null) {
                model.addAttribute("dentiste", dentiste);
                return "dentiste"; // Retourne la vue dentiste si l'utilisateur est authentifié et trouvé
            } else {
                model.addAttribute("error", "Dentiste not found");
                return "error"; // Retourne une vue d'erreur si le dentiste n'est pas trouvé
            }
        } else {
            return "redirect:/login"; // Redirection vers la page de login si l'utilisateur n'est pas authentifié
        }
    }

    @GetMapping("/patients")
    public String listPatientsByDentiste(Model model) {
        if (!authenticated) {
            return "redirect:/login"; // Redirection vers la page de login si l'utilisateur n'est pas authentifié
        }
        List<DossierMedical> dossiersMedicaux = dossierMedicalService.findAll();
        // Récupérer les patients à partir des dossiers médicaux
        List<Patient> patients = dossiersMedicaux.stream()
                .map(DossierMedical::getPatient)
                .collect(Collectors.toList());
        // Ajouter la liste des patients au modèle pour l'affichage dans la vue
        model.addAttribute("patients", patients);
        return "patients"; // Retourne la vue patients.html avec les données des patients
    }

    @PostMapping("/addpatient")
    public String addPatient(@ModelAttribute("patient") Patient patient, Model model) {
        // Récupérer le dentiste (assurez-vous que cette partie fonctionne correctement)
        Dentiste dentiste = dentisteService.trouve();

        // Créer et sauvegarder le dossier médical
        DossierMedical dossierMedical = new DossierMedical();
        dossierMedical.setNumeroDossier(UUID.randomUUID().toString());
        dossierMedical.setDateCreation(LocalDate.now());
        dossierMedical.setMedecinTraitant(dentiste);
        dossierMedicalRepo.save(dossierMedical); // Sauvegarde le dossier médical
        patient.setDossierMedical(dossierMedical);
        // Enregistrer le patient dans la base de données
        patientService.ajouter(patient); // Assure que le patient est sauvegardé avec un ID généré
        // Rediriger vers une page de confirmation ou une autre vue après l'ajout
        return "redirect:/patients"; // Redirection vers une page de confirmation
    }

    @PostMapping("/deletepatient")
    public String deletePatient(@RequestParam("patientId") Long patientId) {
        Patient patient = patientService.findById(patientId);
        if (patient != null) {
            dossierMedicalRepo.delete(patient.getDossierMedical());
            patientService.delete(patientId);
        }
        return "redirect:/patients"; // Redirect to the patients list after deletion
    }

    @GetMapping("/dossierMedical/{numeroDossier}")
    public String afficherDossierMedical(@PathVariable("numeroDossier") String numeroDossier, Model model) {
        DossierMedical dossierMedical = dossierMedicalService.findByNumeroDossier(numeroDossier);
        if (dossierMedical != null) {
            System.out.println(dossierMedical.getNumeroDossier());
            model.addAttribute("dossierMedical", dossierMedical);
            model.addAttribute("patient", dossierMedical.getPatient());
            model.addAttribute("consultation", new Consultation()); // Initialiser une nouvelle consultation
            model.addAttribute("facture", new Facture()); // Initialiser une nouvelle facture
            return "dossierMedical"; // Nom de la vue dossiermedical.html
        } else {
            return "error"; // Gérer le cas où le dossier médical n'est pas trouvé
        }
    }

    @PostMapping("/dossierMedical/{numeroDossier}/ajouterConsultation")
    public String ajouterConsultation(@ModelAttribute("consultation") Consultation consultation,
                                      @PathVariable("numeroDossier") String numeroDossier,
                                      RedirectAttributes redirectAttributes) {
        System.out.println("Méthode ajouterConsultation appelée"); // Log statement

        // Trouver le dossier médical associé au numéro de dossier
        DossierMedical dossierMedical = dossierMedicalService.findByNumeroDossier(numeroDossier);

        if (dossierMedical != null) {
            // Associer la consultation au dossier médical
            consultation.setDossierMedical(dossierMedical);

            // Ajouter la consultation à la liste des consultations du dossier médical
            List<Consultation> consultations = dossierMedical.getConsultations();
            if (consultations == null) {
                consultations = new ArrayList<>();
                dossierMedical.setConsultations(consultations);
            }
            consultations.add(consultation);

            consultationService.ajouter(consultation);

            // Sauvegarder la consultation et le dossier médical mis à jour
            dossierMedicalRepo.save(dossierMedical);

            // Ajouter un message de succès
            redirectAttributes.addFlashAttribute("successMessage", "Consultation ajoutée avec succès.");
            return "redirect:/dossierMedical/" + numeroDossier;
        } else {
            // Ajouter un message d'erreur
            redirectAttributes.addFlashAttribute("errorMessage", "Dossier médical non trouvé.");
            return "redirect:/error";
        }
    }

    @PostMapping("/dossierMedical/{numeroDossier}/deleteConsultation")
    public String deleteConsultation(@PathVariable("numeroDossier") String numeroDossier,
                                     @RequestParam("consultationId") Long consultationId,
                                     RedirectAttributes redirectAttributes) {
        DossierMedical dossierMedical = dossierMedicalService.findByNumeroDossier(numeroDossier);
        if (dossierMedical != null) {
            Consultation consultation = consultationService.trouve(consultationId);
            if (consultation != null && consultation.getDossierMedical().equals(dossierMedical)) {
                consultationService.supprimer(consultationId);  // Assume `supprimer` is the method to delete the consultation
                redirectAttributes.addFlashAttribute("successMessage", "Consultation supprimée avec succès.");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Consultation non trouvée ou n'appartient pas au dossier médical.");
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Dossier médical non trouvé.");
        }
        return "redirect:/dossierMedical/" + numeroDossier;
    }

    @PostMapping("/dossierMedical/{numeroDossier}/ajouterFacture")
    public String ajouterFacture(@ModelAttribute("facture") Facture facture,
                                 @PathVariable("numeroDossier") String numeroDossier,
                                 RedirectAttributes redirectAttributes) {
        System.out.println("Méthode ajouterFacture appelée"); // Log statement

        // Trouver le dossier médical associé au numéro de dossier
        DossierMedical dossierMedical = dossierMedicalService.findByNumeroDossier(numeroDossier);

        if (dossierMedical != null) {
            // Associer la facture au dossier médical
            facture.setDossierMedical(dossierMedical);

            // Ajouter la facture à la liste des factures du dossier médical
            List<Facture> factures = dossierMedical.getFactures();
            if (factures == null) {
                factures = new ArrayList<>();
                dossierMedical.setFactures(factures);
            }
            factures.add(facture);

            factureRepo.save(facture);

            // Sauvegarder la facture et le dossier médical mis à jour
            dossierMedicalRepo.save(dossierMedical);

            // Ajouter un message de succès
            redirectAttributes.addFlashAttribute("successMessage", "Facture ajoutée avec succès.");
            return "redirect:/dossierMedical/" + numeroDossier;
        } else {
            // Ajouter un message d'erreur
            redirectAttributes.addFlashAttribute("errorMessage", "Dossier médical non trouvé.");
            return "redirect:/error";
        }
    }

    @PostMapping("/dossierMedical/{numeroDossier}/deleteFacture")
    public String deleteFacture(@PathVariable("numeroDossier") String numeroDossier,
                                @RequestParam("factureId") Long factureId,
                                RedirectAttributes redirectAttributes) {
        DossierMedical dossierMedical = dossierMedicalService.findByNumeroDossier(numeroDossier);
        if (dossierMedical != null) {
            Facture facture = factureService.trouveparid(factureId);
           //Facture facture=new Facture();

            if (facture != null && facture.getDossierMedical().equals(dossierMedical)) {
                factureRepo.deleteById(factureId);  // Assume `supprimer` is the method to delete the facture
                redirectAttributes.addFlashAttribute("successMessage", "Facture supprimée avec succès.");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Facture non trouvée ou n'appartient pas au dossier médical.");
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Dossier médical non trouvé.");
        }
        return "redirect:/dossierMedical/" + numeroDossier;
    }
}
