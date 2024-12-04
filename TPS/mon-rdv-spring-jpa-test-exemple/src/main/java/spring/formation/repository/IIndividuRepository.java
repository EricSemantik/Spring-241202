package spring.formation.repository;

import java.util.List;
import java.util.Optional;

import spring.formation.model.Individu;
import spring.formation.model.Patient;
import spring.formation.model.Praticien;
import spring.formation.model.Secretaire;

public interface IIndividuRepository extends IRepository<Individu, Long>{
	List<Patient> findAllPatient();
	
	List<Praticien> findAllPraticien();
	
	List<Secretaire> findAllSecretaire();
	
	Optional<Individu> findByIdentifiant(String identifiant);
	
	List<Praticien> findAllPraticienBySpecialite(String nom);
}
