package spring.formation.test;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import spring.formation.MonRdvApplication;
import spring.formation.model.Adresse;
import spring.formation.model.Civilite;
import spring.formation.model.Convention;
import spring.formation.model.Individu;
import spring.formation.model.Praticien;

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
		// ARRANGE
		
		// ACT
		
		// ASSERT
	}
	
	@Test
	public void testFindByIdentifiant() {
		// ARRANGE
		
		// ACT
		
		// ASSERT
	}
	
	
}

