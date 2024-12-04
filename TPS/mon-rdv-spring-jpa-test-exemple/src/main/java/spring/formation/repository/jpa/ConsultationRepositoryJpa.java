package spring.formation.repository.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import spring.formation.model.Consultation;
import spring.formation.model.Statut;
import spring.formation.repository.IConsultationRepository;

@Repository
@Transactional(readOnly = true)
public class ConsultationRepositoryJpa implements IConsultationRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Consultation> findAll() {
		TypedQuery<Consultation> query = em.createQuery("select c from Consultation c", Consultation.class);

		return query.getResultList();
	}

	@Override
	public Optional<Consultation> findById(Long id) {
		Consultation obj = em.find(Consultation.class, id);
		if (obj != null)
			return Optional.of(obj);

		return Optional.empty();
	}

	@Override
	@Transactional(readOnly = false)
	public Consultation save(Consultation obj) {
		return em.merge(obj);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteById(Long id) {
		Query query = em.createQuery("delete from Consultation c where c.id = ?1");
		query.setParameter(1, id);

		int rows = query.executeUpdate();

		return rows > 0;
	}

	@Override
	public List<Consultation> findAllByPraticien(Long idPraticien) {
		TypedQuery<Consultation> query = em.createQuery("select c from Consultation c where c.praticien.id = ?1",
				Consultation.class);
		query.setParameter(1, idPraticien);

		return query.getResultList();
	}

	@Override
	public List<Consultation> findAllByPatient(Long idPatient) {
		TypedQuery<Consultation> query = em.createQuery(
				"select c from Consultation c join c.patient p where p.id = :idPatient", Consultation.class);
		query.setParameter("idPatient", idPatient);

		return query.getResultList();

	}

	@Override
	public List<Consultation> findParStatut(Statut statut) {
		TypedQuery<Consultation> query = em.createQuery(
				"select c from Consultation c where c.statut = :stat", Consultation.class);
		query.setParameter("stat", statut);

		return query.getResultList();
	}

}
