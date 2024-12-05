package spring.formation.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import spring.formation.model.Adresse;
import spring.formation.model.Civilite;
import spring.formation.model.Convention;
import spring.formation.model.Individu;
import spring.formation.model.Praticien;
import spring.formation.model.Role;
import spring.formation.model.Secretaire;
import spring.formation.model.Specialite;
import spring.formation.model.Utilisateur;
import spring.formation.repository.IIndividuRepository;
import spring.formation.repository.ISpecialiteRepository;
import spring.formation.repository.IUtilisateurRepository;

@SpringBootTest
public class PraticienTest {

	@Autowired
	private IIndividuRepository individuRepository;
	@Autowired
	private ISpecialiteRepository specialiteRepository;
	@Autowired
	private IUtilisateurRepository utilisateurRepository;

	@Test
	public void update() {
		// ARRANGE
		Praticien praticien = new Praticien(Civilite.DRE, "BACHELIER", "dominique", "dominique@gmail.com", "0606066666",
				Convention.SECTEUR_3, true, true, true, false);
		praticien.setAdresse(new Adresse("8 avenue Foucault", "", "69000", "Lyon"));

		praticien = (Praticien) individuRepository.save(praticien);

		// ACT
		praticien.setCivilite(Civilite.DR);
		praticien.setNom("DOMENECH");
		praticien.setPrenom("Matthias");
		praticien.setEmail("matthias@gmail.com");
		praticien.setTelephone("0505050505");
		praticien.setConvention(Convention.SECTEUR_1);
		praticien.setCarteBancaire(true);
		praticien.setCarteVitale(false);
		praticien.setCheque(false);
		praticien.setEspece(false);
		praticien.setAdresse(new Adresse("8 rue de la Paix", "2ème étage", "75008", "Paris"));

		praticien = (Praticien) individuRepository.save(praticien);

		// ASSERT
		Optional<Individu> optIndividu = individuRepository.findById(praticien.getId());

		if (optIndividu.isEmpty()) {
			fail("Praticien " + praticien.getId() + " non trouvée");
		} else {
			Praticien praticienFind = (Praticien) optIndividu.get();

			assertEquals(Civilite.DR, praticienFind.getCivilite());
			assertEquals("DOMENECH", praticienFind.getNom());
			assertEquals("Matthias", praticienFind.getPrenom());
			assertEquals("matthias@gmail.com", praticienFind.getEmail());
			assertEquals("0505050505", praticienFind.getTelephone());
			assertEquals(Convention.SECTEUR_1, praticienFind.getConvention());
			assertTrue(praticienFind.isCarteBancaire());
			assertFalse(praticienFind.isCarteVitale());
			assertFalse(praticienFind.isCheque());
			assertFalse(praticienFind.isEspece());
			assertEquals(new Adresse("8 rue de la Paix", "2ème étage", "75008", "Paris"),
					praticienFind.getAdresse());

		}
	}

	@Test
	public void testFindAllPraticienBySpecialite() {
		int startSize = individuRepository.findAllPraticienBySpecialite("Généraliste").size();

		// ARRANGE
		Specialite specialite01 = new Specialite("Généraliste", "Médecine Générale");
		specialite01 = specialiteRepository.save(specialite01);

		Specialite specialite02 = new Specialite("ORL", "Oto-rhino-laryngologiste");
		specialite02 = specialiteRepository.save(specialite02);

		Specialite specialite03 = new Specialite("Sport", "Médecine du sport");
		specialite03 = specialiteRepository.save(specialite03);

		Praticien praticien01 = new Praticien(Civilite.DR, "MAKI", "Raymond", "raymond@gmail.com", "0606066666",
				Convention.SECTEUR_2, true, false, true, true);
		praticien01.getSpecialites().add(specialite01);
		praticien01.getSpecialites().add(specialite03);

		praticien01 = (Praticien) individuRepository.save(praticien01);

		Praticien praticien02 = new Praticien(Civilite.DRE, "BACHELIER", "dominique", "dominique@gmail.com",
				"0606066666", Convention.SECTEUR_3, true, true, true, false);
		praticien02.setAdresse(new Adresse("8 avenue Foucault", "", "69000", "Lyon"));
		praticien02.getSpecialites().add(specialite01);
		praticien02.getSpecialites().add(specialite02);

		praticien02 = (Praticien) individuRepository.save(praticien02);
		// ACT
		int endSize = individuRepository.findAllPraticienBySpecialite("Généraliste").size();

		// ASSERT
		assertEquals(2, endSize - startSize);
	}

	@Test
	public void testFindByIdentifiant() {
		// ARRANGE
		Utilisateur utiSecretaire01 = new Utilisateur("secretaire01", "123456", true, Role.SECRETAIRE);
		utiSecretaire01 = utilisateurRepository.save(utiSecretaire01);

		Secretaire secretaire01 = new Secretaire(Civilite.M, "AMCRO", "Gilles", "gilles@gmail.com", "0606066666");
		secretaire01.setUtilisateur(utiSecretaire01);

		secretaire01 = (Secretaire) individuRepository.save(secretaire01);
		// ACT
		Optional<Individu> optIndividu = individuRepository.findByIdentifiant("secretaire01");

		// ASSERT
		assertTrue(optIndividu.isPresent());
	}

}
