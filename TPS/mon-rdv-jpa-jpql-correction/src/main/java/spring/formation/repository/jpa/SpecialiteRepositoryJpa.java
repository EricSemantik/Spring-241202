package spring.formation.repository.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import spring.formation.MonRdvApplication;
import spring.formation.model.Specialite;
import spring.formation.repository.ISpecialiteRepository;

public class SpecialiteRepositoryJpa implements ISpecialiteRepository {

	@Override
	public List<Specialite> findAll() {
		List<Specialite> liste = new ArrayList<Specialite>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = MonRdvApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Specialite> query = em.createQuery("select s from Specialite s", Specialite.class);

			liste = query.getResultList();

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return liste;
	}

	@Override
	public Optional<Specialite> findById(Long id) {
		Optional<Specialite> optional = Optional.empty();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = MonRdvApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Specialite obj = em.find(Specialite.class, id);

			optional = Optional.of(obj);

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return optional;
	}

	@Override
	public Specialite save(Specialite obj) {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = MonRdvApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			obj = em.merge(obj);

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return obj;
	}

	@Override
	public boolean deleteById(Long id) {
		int rows = 0;

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = MonRdvApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Query query = em.createQuery("delete from Specialite s where s.id = ?1");
			query.setParameter(1, id);

			rows = query.executeUpdate();

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return rows > 0;
	}

}
