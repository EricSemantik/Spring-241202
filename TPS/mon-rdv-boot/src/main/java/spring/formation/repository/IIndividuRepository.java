package spring.formation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.formation.model.Individu;
import spring.formation.model.Patient;
import spring.formation.model.Praticien;
import spring.formation.model.Secretaire;
import spring.formation.repository.custom.IndividuRepositoryCustom;

public interface IIndividuRepository extends JpaRepository<Individu, Long>, IndividuRepositoryCustom {
	@Query("from Patient")
	List<Patient> findAllPatient();
	
	@Query("select p from Praticien p")
	List<Praticien> findAllPraticien();
	
	@Query("from Secretaire")
	List<Secretaire> findAllSecretaire();
	
	@Query("select i from Individu i where i.utilisateur.identifiant = ?1")
	Optional<Individu> findByIdentifiant(String identifiant);
	
	Optional<Individu> findByUtilisateurIdentifiant(String identifiant);
	
	@Query("select p from Praticien p join p.specialites s where s.nom = :nom")
	List<Praticien> findAllPraticienBySpecialite(@Param("nom") String nom);
	
	@Query("select distinct p from Praticien p left join fetch p.specialites s where p.id = ?1")
	Optional<Praticien> findPraticienByIdWithSpecialites(Long id);
}
