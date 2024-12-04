package spring.formation.repository.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import spring.formation.model.Specialite;
import spring.formation.repository.ISpecialiteRepository;

@Repository
@Transactional(readOnly = true)
public class SpecialiteRepositoryJpa implements ISpecialiteRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Specialite> findAll() {
		TypedQuery<Specialite> query = em.createQuery("select s from Specialite s", Specialite.class);

		return query.getResultList();
	}

	@Override
	public Optional<Specialite> findById(Long id) {
		Specialite obj = em.find(Specialite.class, id);

		if (obj != null)
			return Optional.of(obj);

		return Optional.empty();
	}

	@Override
	@Transactional(readOnly = false)
	public Specialite save(Specialite obj) {
		return em.merge(obj);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteById(Long id) {
		Query query = em.createQuery("delete from Specialite s where s.id = ?1");
		query.setParameter(1, id);

		int rows = query.executeUpdate();

		return rows > 0;
	}

}
