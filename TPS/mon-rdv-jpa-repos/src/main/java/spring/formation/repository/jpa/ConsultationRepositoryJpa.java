package spring.formation.repository.jpa;

import java.util.List;
import java.util.Optional;

import spring.formation.model.Consultation;
import spring.formation.repository.IConsultationRepository;

public class ConsultationRepositoryJpa implements IConsultationRepository {

	@Override
	public List<Consultation> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Consultation> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Consultation save(Consultation obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
