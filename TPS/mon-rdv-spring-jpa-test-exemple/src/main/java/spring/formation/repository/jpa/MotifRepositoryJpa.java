package spring.formation.repository.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import spring.formation.model.Motif;
import spring.formation.repository.IMotifRepository;

@Repository
@Transactional(readOnly = true)
public class MotifRepositoryJpa implements IMotifRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Motif> findAll() {
		TypedQuery<Motif> query = em.createQuery("select m from Motif m", Motif.class);

		return query.getResultList();
	}

	@Override
	public Optional<Motif> findById(Long id) {
		Motif obj = em.find(Motif.class, id);

		if (obj != null)
			return Optional.of(obj);

		return Optional.empty();
	}

	@Override
	@Transactional(readOnly = false)
	public Motif save(Motif obj) {
		return em.merge(obj);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteById(Long id) {
		Query query = em.createQuery("delete from Motif m where m.id = ?1");
		query.setParameter(1, id);

		int rows = query.executeUpdate();

		return rows > 0;
	}

}
