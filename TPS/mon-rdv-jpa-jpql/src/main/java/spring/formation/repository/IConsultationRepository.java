package spring.formation.repository;

import java.util.List;

import spring.formation.model.Consultation;

public interface IConsultationRepository extends IRepository<Consultation, Long> {
	List<Consultation> findAllByPraticien(Long idPraticien);
	List<Consultation> findAllByPatient(Long idPatient);
}
