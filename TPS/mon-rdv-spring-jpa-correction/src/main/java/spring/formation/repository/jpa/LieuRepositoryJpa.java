package spring.formation.repository.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import spring.formation.model.Lieu;
import spring.formation.repository.ILieuRepository;

@Repository
@Transactional(readOnly = true)
public class LieuRepositoryJpa implements ILieuRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Lieu> findAll() {
		TypedQuery<Lieu> query = em.createQuery("select l from Lieu l", Lieu.class);

		return query.getResultList();
	}

	@Override
	public Optional<Lieu> findById(Long id) {
		Lieu obj = em.find(Lieu.class, id);

		if (obj != null)
			return Optional.of(obj);

		return Optional.empty();
	}

	@Override
	@Transactional(readOnly = false)
	public Lieu save(Lieu obj) {
		return em.merge(obj);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteById(Long id) {
		Query query = em.createQuery("delete from Lieu l where l.id = ?1");
		query.setParameter(1, id);

		int rows = query.executeUpdate();

		return rows > 0;
	}

}
