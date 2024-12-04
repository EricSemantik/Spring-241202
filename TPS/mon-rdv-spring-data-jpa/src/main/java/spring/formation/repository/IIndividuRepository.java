package spring.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.formation.model.Individu;

public interface IIndividuRepository extends JpaRepository<Individu, Long>{
//	List<Patient> findAllPatient();
//	
//	List<Praticien> findAllPraticien();
//	
//	List<Secretaire> findAllSecretaire();
//	
//	Optional<Individu> findByIdentifiant(String identifiant);
//	
//	List<Praticien> findAllPraticienBySpecialite(String nom);
}
