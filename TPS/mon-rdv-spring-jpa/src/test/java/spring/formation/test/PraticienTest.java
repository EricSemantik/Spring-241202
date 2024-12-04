package spring.formation.test;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import spring.formation.MonRdvApplication;
import spring.formation.model.Adresse;
import spring.formation.model.Civilite;
import spring.formation.model.Convention;
import spring.formation.model.Individu;
import spring.formation.model.Praticien;
import spring.formation.model.Role;
import spring.formation.model.Secretaire;
import spring.formation.model.Specialite;
import spring.formation.model.Utilisateur;

public class PraticienTest {
	@Test
	public void update() {
		// ARRANGE
		Praticien praticien = new Praticien(Civilite.DRE, "BACHELIER", "dominique", "dominique@gmail.com",
				"0606066666", Convention.SECTEUR_3, true, true, true, false);
		praticien.setAdresse(new Adresse("8 avenue Foucault", "", "69000", "Lyon"));

		praticien = (Praticien) MonRdvApplication.getInstance().getIndividuRepository().save(praticien);	
		
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
		
		praticien = (Praticien) MonRdvApplication.getInstance().getIndividuRepository().save(praticien);	
		
		// ASSERT
		Optional<Individu> optIndividu = MonRdvApplication.getInstance().getIndividuRepository().findById(praticien.getId());
		
		if(optIndividu.isEmpty()) {
			Assert.fail("Praticien " + praticien.getId() + " non trouvée");
		} else {
			Praticien praticienFind = (Praticien) optIndividu.get();
			
			Assert.assertEquals(Civilite.DR, praticienFind.getCivilite());
			Assert.assertEquals("DOMENECH", praticienFind.getNom());
			Assert.assertEquals("Matthias", praticienFind.getPrenom());
			Assert.assertEquals("matthias@gmail.com", praticienFind.getEmail());
			Assert.assertEquals("0505050505", praticienFind.getTelephone());
			Assert.assertEquals(Convention.SECTEUR_1, praticienFind.getConvention());
			Assert.assertTrue(praticienFind.isCarteBancaire());
			Assert.assertFalse(praticienFind.isCarteVitale());
			Assert.assertFalse(praticienFind.isCheque());
			Assert.assertFalse(praticienFind.isEspece());
			Assert.assertEquals(new Adresse("8 rue de la Paix", "2ème étage", "75008", "Paris"), praticienFind.getAdresse());
			
		}

		
				
	}
	
	@Test
	public void testFindAllPraticienBySpecialite() {
		int startSize = MonRdvApplication.getInstance().getIndividuRepository().findAllPraticienBySpecialite("Généraliste").size();
		
		// ARRANGE
		Specialite specialite01 = new Specialite("Généraliste", "Médecine Générale");
		specialite01 = MonRdvApplication.getInstance().getSpecialiteRepository().save(specialite01);

		Specialite specialite02 = new Specialite("ORL", "Oto-rhino-laryngologiste");
		specialite02 = MonRdvApplication.getInstance().getSpecialiteRepository().save(specialite02);
		
		Specialite specialite03 = new Specialite("Sport", "Médecine du sport");
		specialite03 = MonRdvApplication.getInstance().getSpecialiteRepository().save(specialite03);
		
		Praticien praticien01 = new Praticien(Civilite.DR, "MAKI", "Raymond", "raymond@gmail.com", "0606066666",
				Convention.SECTEUR_2, true, false, true, true);
		praticien01.getSpecialites().add(specialite01);
		praticien01.getSpecialites().add(specialite03);

		praticien01 = (Praticien) MonRdvApplication.getInstance().getIndividuRepository().save(praticien01);
		
		Praticien praticien02 = new Praticien(Civilite.DRE, "BACHELIER", "dominique", "dominique@gmail.com",
				"0606066666", Convention.SECTEUR_3, true, true, true, false);
		praticien02.setAdresse(new Adresse("8 avenue Foucault", "", "69000", "Lyon"));
		praticien02.getSpecialites().add(specialite01);
		praticien02.getSpecialites().add(specialite02);

		praticien02 = (Praticien) MonRdvApplication.getInstance().getIndividuRepository().save(praticien02);
		// ACT
		int endSize = MonRdvApplication.getInstance().getIndividuRepository().findAllPraticienBySpecialite("Généraliste").size();
		
		// ASSERT
		Assert.assertEquals(2, endSize - startSize);
	}
	
	@Test
	public void testFindByIdentifiant() {
		// ARRANGE
		Utilisateur utiSecretaire01 = new Utilisateur("secretaire01", "123456", true, Role.SECRETAIRE);
		utiSecretaire01 = MonRdvApplication.getInstance().getUtilisateurRepository().save(utiSecretaire01);
		
		Secretaire secretaire01 = new Secretaire(Civilite.M, "AMCRO", "Gilles", "gilles@gmail.com", "0606066666");
		secretaire01.setUtilisateur(utiSecretaire01);

		secretaire01 = (Secretaire) MonRdvApplication.getInstance().getIndividuRepository().save(secretaire01);	
		// ACT
		Optional<Individu> optIndividu = MonRdvApplication.getInstance().getIndividuRepository().findByIdentifiant("secretaire01");
		
		// ASSERT
		assertTrue(optIndividu.isPresent());
	}
	
	
}

