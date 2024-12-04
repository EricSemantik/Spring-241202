package spring.formation.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.formation.config.JPAConfiguration;
import spring.formation.model.Adresse;
import spring.formation.model.Civilite;
import spring.formation.model.Consultation;
import spring.formation.model.Convention;
import spring.formation.model.Individu;
import spring.formation.model.Lieu;
import spring.formation.model.Motif;
import spring.formation.model.Patient;
import spring.formation.model.Praticien;
import spring.formation.model.Role;
import spring.formation.model.Secretaire;
import spring.formation.model.Specialite;
import spring.formation.model.Statut;
import spring.formation.model.Utilisateur;
import spring.formation.repository.IConsultationRepository;
import spring.formation.repository.IIndividuRepository;
import spring.formation.repository.ILieuRepository;
import spring.formation.repository.IMotifRepository;
import spring.formation.repository.ISpecialiteRepository;
import spring.formation.repository.IUtilisateurRepository;

public class MonRdvApplicationTest {

	@Test
	public void populate() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JPAConfiguration.class);

		IConsultationRepository consultationRepository = context.getBean(IConsultationRepository.class);
		IIndividuRepository individuRepository = context.getBean(IIndividuRepository.class);
		ILieuRepository lieuRepository = context.getBean(ILieuRepository.class);
		IMotifRepository motifRepository = context.getBean(IMotifRepository.class);
		ISpecialiteRepository specialiteRepository = context.getBean(ISpecialiteRepository.class);
		IUtilisateurRepository utilisateurRepository = context.getBean(IUtilisateurRepository.class);

		Utilisateur utiAdmin = new Utilisateur("admin", "123456", true, Role.ADMIN);
		utiAdmin = utilisateurRepository.save(utiAdmin);

		Utilisateur utiPatient01 = new Utilisateur("patient01", "123456", true, Role.PATIENT);
		utiPatient01 = utilisateurRepository.save(utiPatient01);

		Utilisateur utiPatient02 = new Utilisateur("patient02", "123456", true, Role.PATIENT);
		utiPatient02 = utilisateurRepository.save(utiPatient02);

		Utilisateur utiPraticien01 = new Utilisateur("praticien01", "123456", true, Role.PRATICIEN);
		utiPraticien01 = utilisateurRepository.save(utiPraticien01);

		Utilisateur utiPraticien02 = new Utilisateur("praticien02", "123456", false, Role.PRATICIEN);
		utiPraticien02 = utilisateurRepository.save(utiPraticien02);

		Utilisateur utiSecretaire01 = new Utilisateur("secretaire01", "123456", true, Role.SECRETAIRE);
		utiSecretaire01 = utilisateurRepository.save(utiSecretaire01);

		Patient patient01 = new Patient(Civilite.M, "DUPONT", "Pierre", "pierre@gmail.com", "0606060606");
		patient01.setDtNaissance(LocalDate.of(1965, 3, 15));
		patient01.setLieuNaissance("Angers");
		patient01.setNumeroSS("165034412245124");
		patient01.setAdresse(new Adresse("1 rue de Rennes", "", "44310", "Nantes"));
		patient01.setUtilisateur(utiPatient01);

		patient01 = (Patient) individuRepository.save(patient01);

		Patient patient02 = new Patient(Civilite.MME, "VEIL", "Simone", "simone@gmail.com", "0606066666");
		patient02.setDtNaissance(LocalDate.of(1956, 3, 15));
		patient02.setLieuNaissance("Angers");
		patient02.setNumeroSS("256034412245124");
		patient02.setAdresse(new Adresse("13 rue montparnnasse", "", "75014", "Paris"));
		patient02.setUtilisateur(utiPatient02);

		patient02 = (Patient) individuRepository.save(patient02);

		Specialite specialite01 = new Specialite("Généraliste", "Médecine Générale");
		specialite01 = specialiteRepository.save(specialite01);

		Specialite specialite02 = new Specialite("ORL", "Oto-rhino-laryngologiste");
		specialite02 = specialiteRepository.save(specialite02);

		Lieu lieu01 = new Lieu(new Adresse("91 Rue de Rivière", "3ème étage", "33000", "Bordeaux"), "Clinique TIVOLI");

		lieu01 = lieuRepository.save(lieu01);

		Lieu lieu02 = new Lieu(new Adresse("18 avenue du Général de Gaulle", "", "33290", "Blanquefort"),
				"Cabinet ORL");

		lieu02 = lieuRepository.save(lieu02);

		Lieu lieu03 = new Lieu(
				new Adresse("Place Amélie Raba Léon", "Service de consultation ORL", "33000", "Bordeaux"),
				"CHU Pellegrin");

		lieu03 = lieuRepository.save(lieu03);

		Praticien praticien01 = new Praticien(Civilite.DR, "MAKI", "Raymond", "raymond@gmail.com", "0606066666",
				Convention.SECTEUR_2, true, false, true, true);
		praticien01.setAdresse(new Adresse("5 rue de Toulouse", "", "33000", "Bordeaux"));
		praticien01.setUtilisateur(utiPraticien01);
		praticien01.getLieux().add(lieu01);
		praticien01.getLieux().add(lieu03);
		praticien01.getSpecialites().add(specialite01);

		praticien01 = (Praticien) individuRepository.save(praticien01);

		Motif motif01 = new Motif("Première Consultation en Médecine Générale", 30, 32.0, praticien01);

		motif01 = motifRepository.save(motif01);

		Motif motif02 = new Motif("Consultation de suivi", 20, 32.0, praticien01);

		motif02 = motifRepository.save(motif02);

		Motif motif03 = new Motif("Urgence", 15, 32.0, praticien01);

		motif03 = motifRepository.save(motif03);

		Praticien praticien02 = new Praticien(Civilite.DRE, "BACHELIER", "dominique", "dominique@gmail.com",
				"0606066666", Convention.SECTEUR_3, true, true, true, false);
		praticien02.setAdresse(new Adresse("8 avenue Foucault", "", "69000", "Lyon"));
		praticien02.setUtilisateur(utiPraticien02);
		praticien02.getLieux().add(lieu01);
		praticien02.getLieux().add(lieu02);
		praticien02.getSpecialites().add(specialite02);

		praticien02 = (Praticien) individuRepository.save(praticien02);

		Motif motif04 = new Motif("Première Consultation ORL", 30, 65.0, praticien02);

		motif04 = motifRepository.save(motif04);

		Motif motif05 = new Motif("Consultation de suivi ORL", 20, 65.0, praticien02);

		motif05 = motifRepository.save(motif05);

		Secretaire secretaire01 = new Secretaire(Civilite.M, "AMCRO", "Gilles", "gilles@gmail.com", "0606066666");
		secretaire01.setUtilisateur(utiSecretaire01);
		secretaire01.getPraticiens().add(praticien01);
		secretaire01.getPraticiens().add(praticien02);

		secretaire01 = (Secretaire) individuRepository.save(secretaire01);

		Consultation consultation01 = new Consultation(LocalDateTime.of(2024, Month.JUNE, 13, 14, 30), 30,
				"Première Consultation en Médecine Générale", 32.0, Statut.EN_ATTENTE);
		consultation01.setPatient(patient01);
		consultation01.setPraticien(praticien01);
		consultation01.setLieu(lieu01);

		consultation01 = consultationRepository.save(consultation01);

		Consultation consultation02 = new Consultation(LocalDateTime.of(2024, Month.JUNE, 15, 9, 0), 20,
				"Consultation de suivi ORL", 65.0, Statut.CONFIRMER);
		consultation02.setPatient(patient02);
		consultation02.setPraticien(praticien02);
		consultation02.setLieu(lieu02);

		consultation02 = consultationRepository.save(consultation02);

		List<Consultation> consultationsPraticien = consultationRepository.findAllByPraticien(praticien01.getId());

		System.out.println("consultationsPraticien=" + consultationsPraticien.size());

		List<Consultation> consultationsPatient = consultationRepository.findAllByPatient(patient01.getId());

		System.out.println("consultationsPatient=" + consultationsPatient.size());

		List<Patient> patients = individuRepository.findAllPatient();

		System.out.println("patients=" + patients.size());

		Optional<Individu> optIndividu = individuRepository.findByIdentifiant("secretaire01");

		System.out.println("optIndividu=" + optIndividu.isPresent());

		List<Praticien> praticiens = individuRepository.findAllPraticienBySpecialite("ORL");

		System.out.println("praticiens=" + praticiens.size());

		Optional<Utilisateur> optUtilisateur = utilisateurRepository.findByIdentifiant("patient01");

		System.out.println("optUtilisateur=" + optUtilisateur.isPresent());

		context.close();
	}
}
