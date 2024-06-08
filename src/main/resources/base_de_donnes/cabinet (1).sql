-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 05, 2024 at 07:23 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cabinet`
--

-- --------------------------------------------------------

--
-- Table structure for table `acte`
--

CREATE TABLE `acte` (
  `id_acte` bigint(20) NOT NULL,
  `categorie_acte` tinyint(4) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `prix_de_base` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `antecendent_medical`
--

CREATE TABLE `antecendent_medical` (
  `id` bigint(20) NOT NULL,
  `categorie` enum('MALADIE_CHRONIQUE','CONTRE_INDICATION','MALADIE_HEREDITAIRE','ALLERGIE') DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `caisse`
--

CREATE TABLE `caisse` (
  `id_caisse` bigint(20) NOT NULL,
  `recette_delannee` double DEFAULT NULL,
  `recette_du_jours` double DEFAULT NULL,
  `recette_du_mois` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `consultation`
--

CREATE TABLE `consultation` (
  `id_consultation` bigint(20) NOT NULL,
  `date_consultation` date DEFAULT NULL,
  `type_consultation` enum('CONSULTATION_GENERALE','SUIVI','URGENCE') DEFAULT NULL,
  `dossier_medical_numero_dossier` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `consultation`
--

INSERT INTO `consultation` (`id_consultation`, `date_consultation`, `type_consultation`, `dossier_medical_numero_dossier`) VALUES
(10, '2024-05-01', 'SUIVI', 'd8e24bb6-b0da-44b6-878b-efe02b5fe07b'),
(19, '2024-05-09', 'SUIVI', 'd4f59b58-3c4e-4188-b423-99b175c67b78'),
(20, '2024-06-14', 'URGENCE', 'd4f59b58-3c4e-4188-b423-99b175c67b78'),
(21, '2024-06-11', 'SUIVI', 'd4f59b58-3c4e-4188-b423-99b175c67b78');

-- --------------------------------------------------------

--
-- Table structure for table `dentiste`
--

CREATE TABLE `dentiste` (
  `assurance` enum('AUTRE','CIMR','CNOPS','CNSS') DEFAULT NULL,
  `date_retour_conge` date DEFAULT NULL,
  `salaire_de_base` double DEFAULT NULL,
  `specialite` enum('ENDODONTIE','CHIRURGIE_DENTAIRE','PROTHÈSE','ORTHODONTIE','PARODONTOLOGIE','AUTRE') DEFAULT NULL,
  `status_actuel` enum('EN_ARRÊT_MALADIE','AUTRE','ENCONGE','INACTIF','ACTIF') DEFAULT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dentiste`
--

INSERT INTO `dentiste` (`assurance`, `date_retour_conge`, `salaire_de_base`, `specialite`, `status_actuel`, `id`) VALUES
('CNSS', '2024-05-25', 12000, 'ENDODONTIE', 'ACTIF', 1);

-- --------------------------------------------------------

--
-- Table structure for table `dentiste_disponibilites`
--

CREATE TABLE `dentiste_disponibilites` (
  `dentiste_id` bigint(20) NOT NULL,
  `disponibilites` tinyint(4) DEFAULT NULL,
  `disponibilites_key` enum('MONDAY','TUESDAY','WEDNESDAY','THURSDAY','FRIDAY','SATURDAY','SUNDAY') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dentiste_disponibilites`
--

INSERT INTO `dentiste_disponibilites` (`dentiste_id`, `disponibilites`, `disponibilites_key`) VALUES
(1, 0, 'MONDAY'),
(1, 1, 'TUESDAY');

-- --------------------------------------------------------

--
-- Table structure for table `dossier_medical`
--

CREATE TABLE `dossier_medical` (
  `numero_dossier` varchar(255) NOT NULL,
  `date_creation` date DEFAULT NULL,
  `statut_paiement` tinyint(4) DEFAULT NULL,
  `medecin_traitant_id` bigint(20) DEFAULT NULL,
  `situation_financiere_id_situation_financiere` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dossier_medical`
--

INSERT INTO `dossier_medical` (`numero_dossier`, `date_creation`, `statut_paiement`, `medecin_traitant_id`, `situation_financiere_id_situation_financiere`) VALUES
('d4f59b58-3c4e-4188-b423-99b175c67b78', '2024-05-25', NULL, 1, NULL),
('d8e24bb6-b0da-44b6-878b-efe02b5fe07b', '2024-05-26', NULL, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `facture`
--

CREATE TABLE `facture` (
  `id_facture` bigint(20) NOT NULL,
  `date_facturation` date DEFAULT NULL,
  `montant_paye` double DEFAULT NULL,
  `montant_restant` double DEFAULT NULL,
  `montant_total` double DEFAULT NULL,
  `type_paiement` tinyint(4) DEFAULT NULL,
  `consultation_id_consultation` bigint(20) DEFAULT NULL,
  `situation_financiere_id_situation_financiere` bigint(20) DEFAULT NULL,
  `dossier_medical_numero_dossier` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `facture`
--

INSERT INTO `facture` (`id_facture`, `date_facturation`, `montant_paye`, `montant_restant`, `montant_total`, `type_paiement`, `consultation_id_consultation`, `situation_financiere_id_situation_financiere`, `dossier_medical_numero_dossier`) VALUES
(3, '2024-06-21', 80, 500, 1000, 0, NULL, NULL, 'd4f59b58-3c4e-4188-b423-99b175c67b78'),
(5, '2024-06-26', 1000, 500, 1500, 0, NULL, NULL, 'd4f59b58-3c4e-4188-b423-99b175c67b78');

-- --------------------------------------------------------

--
-- Table structure for table `intervention_medecin`
--

CREATE TABLE `intervention_medecin` (
  `id_intervention` bigint(20) NOT NULL,
  `dent` bigint(20) DEFAULT NULL,
  `note_medecin` varchar(255) DEFAULT NULL,
  `prix_patient` double DEFAULT NULL,
  `acte_id_acte` bigint(20) DEFAULT NULL,
  `consultation_id_consultation` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `date_naissance` date DEFAULT NULL,
  `groupe_sanguin` tinyint(4) DEFAULT NULL,
  `mutuelle` tinyint(4) DEFAULT NULL,
  `profession` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `dossier_medical_numero_dossier` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`date_naissance`, `groupe_sanguin`, `mutuelle`, `profession`, `id`, `dossier_medical_numero_dossier`) VALUES
(NULL, NULL, NULL, NULL, 10, NULL),
(NULL, NULL, NULL, NULL, 11, NULL),
(NULL, NULL, NULL, NULL, 12, NULL),
('2024-05-03', 0, 0, NULL, 13, NULL),
('2024-05-09', 3, 2, NULL, 14, NULL),
('2024-05-02', 3, 1, NULL, 15, NULL),
('2024-04-30', 2, 0, NULL, 16, NULL),
('2024-05-09', 3, 2, NULL, 17, NULL),
('2024-05-21', 1, 1, NULL, 18, 'd4f59b58-3c4e-4188-b423-99b175c67b78'),
('2024-05-07', 3, 3, NULL, 21, 'd8e24bb6-b0da-44b6-878b-efe02b5fe07b');

-- --------------------------------------------------------

--
-- Table structure for table `patient_antecendent_medicals`
--

CREATE TABLE `patient_antecendent_medicals` (
  `patients_avec_ce_antecendent_medicale_id` bigint(20) NOT NULL,
  `antecendent_medicals_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `personne`
--

CREATE TABLE `personne` (
  `id` bigint(20) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `cin` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `sexe` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `personne`
--

INSERT INTO `personne` (`id`, `adresse`, `cin`, `email`, `nom`, `prenom`, `sexe`, `telephone`) VALUES
(1, 'RABAT HASAN 212', 'A142536', 'elmoutaqui@gmail.com', 'ELMEHDI', 'ELMOUTAQUI', NULL, '060696925'),
(10, NULL, NULL, 'eelmoutaquimehdi@gmail.com', NULL, NULL, NULL, NULL),
(11, NULL, NULL, 'eelmoutaquimehdi@gmail.com', NULL, NULL, NULL, NULL),
(12, NULL, NULL, 'eelmoutaquimehdi@gmail.com', NULL, NULL, NULL, NULL),
(13, NULL, NULL, 'eelmoutaquimehdi@gmail.com', NULL, NULL, NULL, NULL),
(14, 'RABAT , joulan', NULL, 'said@gmail.com', 'said', NULL, 'male', NULL),
(15, 'RABAT , joulan', NULL, 'jawad@gmail.com', 'jawad', NULL, 'male', NULL),
(16, 'RABAT , joulan', NULL, 'jawad@gmail.com', 'jawad', NULL, 'male', NULL),
(17, 'RABAT , joulan', NULL, 'khalid@gmail.com', 'khalid', NULL, 'male', NULL),
(18, 'RABAT , joulan', NULL, 'ahmed@gmail.com', 'ahmed', NULL, 'male', NULL),
(21, 'hay amal', NULL, 'nabil@gmail.con', 'nabil oukadi', NULL, 'male', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `privilege`
--

CREATE TABLE `privilege` (
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `situation_financiere`
--

CREATE TABLE `situation_financiere` (
  `id_situation_financiere` bigint(20) NOT NULL,
  `date_creation` date DEFAULT NULL,
  `montant_global_paye` double DEFAULT NULL,
  `montant_global_restant` double DEFAULT NULL,
  `caisse_id_caisse` bigint(20) DEFAULT NULL,
  `dossier_medical_numero_dossier` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `mot_depass` varchar(255) DEFAULT NULL,
  `nom_utilisateur` varchar(255) DEFAULT NULL,
  `role` tinyint(4) DEFAULT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `utilisateur`
--

INSERT INTO `utilisateur` (`mot_depass`, `nom_utilisateur`, `role`, `id`) VALUES
('12345', 'ELMEHDI', NULL, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `acte`
--
ALTER TABLE `acte`
  ADD PRIMARY KEY (`id_acte`);

--
-- Indexes for table `antecendent_medical`
--
ALTER TABLE `antecendent_medical`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `caisse`
--
ALTER TABLE `caisse`
  ADD PRIMARY KEY (`id_caisse`);

--
-- Indexes for table `consultation`
--
ALTER TABLE `consultation`
  ADD PRIMARY KEY (`id_consultation`),
  ADD KEY `FKrhwhtwh707ehitm070j4fed8m` (`dossier_medical_numero_dossier`);

--
-- Indexes for table `dentiste`
--
ALTER TABLE `dentiste`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dentiste_disponibilites`
--
ALTER TABLE `dentiste_disponibilites`
  ADD PRIMARY KEY (`dentiste_id`,`disponibilites_key`);

--
-- Indexes for table `dossier_medical`
--
ALTER TABLE `dossier_medical`
  ADD PRIMARY KEY (`numero_dossier`),
  ADD UNIQUE KEY `UK_rlq1ciqtxxpvjmvrcovvh7w6i` (`situation_financiere_id_situation_financiere`),
  ADD KEY `FKgm7axk8qswrf6nek1tdhojgdv` (`medecin_traitant_id`);

--
-- Indexes for table `facture`
--
ALTER TABLE `facture`
  ADD PRIMARY KEY (`id_facture`),
  ADD KEY `FKq1esylq2gvfr3sc11aan0mcg5` (`consultation_id_consultation`),
  ADD KEY `FKf09db79plcby625cncxu9t1lh` (`situation_financiere_id_situation_financiere`),
  ADD KEY `FK8fen6dfaxnhppv7p1wg8nfdgw` (`dossier_medical_numero_dossier`);

--
-- Indexes for table `intervention_medecin`
--
ALTER TABLE `intervention_medecin`
  ADD PRIMARY KEY (`id_intervention`),
  ADD KEY `FKt28jdt7iw531jn2ir4oyp4g4c` (`acte_id_acte`),
  ADD KEY `FK11ai0kekhqs8krm3oqex82ucn` (`consultation_id_consultation`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_td3ov2vmionowqmfnbyywdjg0` (`dossier_medical_numero_dossier`);

--
-- Indexes for table `patient_antecendent_medicals`
--
ALTER TABLE `patient_antecendent_medicals`
  ADD KEY `FKe60qgpxjx59w1elbhusvphrkq` (`antecendent_medicals_id`),
  ADD KEY `FKpl7o6chk82i68si9fhs3mvs6o` (`patients_avec_ce_antecendent_medicale_id`);

--
-- Indexes for table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `privilege`
--
ALTER TABLE `privilege`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `situation_financiere`
--
ALTER TABLE `situation_financiere`
  ADD PRIMARY KEY (`id_situation_financiere`),
  ADD UNIQUE KEY `UK_47alsvqg5u41jca36yg6u2jku` (`dossier_medical_numero_dossier`),
  ADD KEY `FKm4na4vteamb3yw7xwq45q6sg9` (`caisse_id_caisse`);

--
-- Indexes for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_qaujre9c9usfu26gt36onlblk` (`nom_utilisateur`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `acte`
--
ALTER TABLE `acte`
  MODIFY `id_acte` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `antecendent_medical`
--
ALTER TABLE `antecendent_medical`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `caisse`
--
ALTER TABLE `caisse`
  MODIFY `id_caisse` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `consultation`
--
ALTER TABLE `consultation`
  MODIFY `id_consultation` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `facture`
--
ALTER TABLE `facture`
  MODIFY `id_facture` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `intervention_medecin`
--
ALTER TABLE `intervention_medecin`
  MODIFY `id_intervention` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `personne`
--
ALTER TABLE `personne`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `situation_financiere`
--
ALTER TABLE `situation_financiere`
  MODIFY `id_situation_financiere` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `consultation`
--
ALTER TABLE `consultation`
  ADD CONSTRAINT `FKrhwhtwh707ehitm070j4fed8m` FOREIGN KEY (`dossier_medical_numero_dossier`) REFERENCES `dossier_medical` (`numero_dossier`);

--
-- Constraints for table `dentiste`
--
ALTER TABLE `dentiste`
  ADD CONSTRAINT `FKq9v6274y20l4h7ekjiy51avjp` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Constraints for table `dentiste_disponibilites`
--
ALTER TABLE `dentiste_disponibilites`
  ADD CONSTRAINT `FKt4fkq97rm2haijiyyjigy0r5e` FOREIGN KEY (`dentiste_id`) REFERENCES `dentiste` (`id`);

--
-- Constraints for table `dossier_medical`
--
ALTER TABLE `dossier_medical`
  ADD CONSTRAINT `FKgm7axk8qswrf6nek1tdhojgdv` FOREIGN KEY (`medecin_traitant_id`) REFERENCES `dentiste` (`id`),
  ADD CONSTRAINT `FKn2p11mek0th2lyewgwgv9nty1` FOREIGN KEY (`situation_financiere_id_situation_financiere`) REFERENCES `situation_financiere` (`id_situation_financiere`);

--
-- Constraints for table `facture`
--
ALTER TABLE `facture`
  ADD CONSTRAINT `FK8fen6dfaxnhppv7p1wg8nfdgw` FOREIGN KEY (`dossier_medical_numero_dossier`) REFERENCES `dossier_medical` (`numero_dossier`),
  ADD CONSTRAINT `FKf09db79plcby625cncxu9t1lh` FOREIGN KEY (`situation_financiere_id_situation_financiere`) REFERENCES `situation_financiere` (`id_situation_financiere`),
  ADD CONSTRAINT `FKq1esylq2gvfr3sc11aan0mcg5` FOREIGN KEY (`consultation_id_consultation`) REFERENCES `consultation` (`id_consultation`);

--
-- Constraints for table `intervention_medecin`
--
ALTER TABLE `intervention_medecin`
  ADD CONSTRAINT `FK11ai0kekhqs8krm3oqex82ucn` FOREIGN KEY (`consultation_id_consultation`) REFERENCES `consultation` (`id_consultation`),
  ADD CONSTRAINT `FKt28jdt7iw531jn2ir4oyp4g4c` FOREIGN KEY (`acte_id_acte`) REFERENCES `acte` (`id_acte`);

--
-- Constraints for table `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `FK1py30rhmxo3rrpovp5nci2klq` FOREIGN KEY (`id`) REFERENCES `personne` (`id`),
  ADD CONSTRAINT `FKbptbqouir191yiacja5vylwft` FOREIGN KEY (`dossier_medical_numero_dossier`) REFERENCES `dossier_medical` (`numero_dossier`);

--
-- Constraints for table `patient_antecendent_medicals`
--
ALTER TABLE `patient_antecendent_medicals`
  ADD CONSTRAINT `FKe60qgpxjx59w1elbhusvphrkq` FOREIGN KEY (`antecendent_medicals_id`) REFERENCES `antecendent_medical` (`id`),
  ADD CONSTRAINT `FKpl7o6chk82i68si9fhs3mvs6o` FOREIGN KEY (`patients_avec_ce_antecendent_medicale_id`) REFERENCES `patient` (`id`);

--
-- Constraints for table `situation_financiere`
--
ALTER TABLE `situation_financiere`
  ADD CONSTRAINT `FKiu8xun509if37ushl6q3vpp6m` FOREIGN KEY (`dossier_medical_numero_dossier`) REFERENCES `dossier_medical` (`numero_dossier`),
  ADD CONSTRAINT `FKm4na4vteamb3yw7xwq45q6sg9` FOREIGN KEY (`caisse_id_caisse`) REFERENCES `caisse` (`id_caisse`);

--
-- Constraints for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `FK2cilsu513owv0einwxeslkygn` FOREIGN KEY (`id`) REFERENCES `personne` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
