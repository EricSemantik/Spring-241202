package spring.formation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import spring.formation.model.Consultation;
import spring.formation.model.Statut;

public interface IConsultationRepository extends JpaRepository<Consultation, Long> {
	List<Consultation> findAllByPraticien(Long idPraticien); // par @NamedQuery

	List<Consultation> findAllByPatient(@Param("idPatient") Long id); // par @NamedQuery
	
	List<Consultation> findByStatut(Statut statut); // par convention de nommage
}
