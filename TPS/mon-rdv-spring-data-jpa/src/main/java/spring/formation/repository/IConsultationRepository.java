package spring.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.formation.model.Consultation;

public interface IConsultationRepository extends JpaRepository<Consultation, Long> {
//	List<Consultation> findAllByPraticien(Long idPraticien);
//	List<Consultation> findAllByPatient(Long idPatient);
//	
//	
//	List<Consultation> findParStatut(Statut statut);
}
