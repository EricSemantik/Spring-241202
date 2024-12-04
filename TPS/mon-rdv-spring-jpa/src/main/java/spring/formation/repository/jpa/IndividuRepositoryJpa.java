package spring.formation.repository.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import spring.formation.MonRdvApplication;
import spring.formation.model.Individu;
import spring.formation.model.Patient;
import spring.formation.model.Praticien;
import spring.formation.model.Secretaire;
import spring.formation.repository.IIndividuRepository;

public class IndividuRepositoryJpa implements IIndividuRepository {

	@Override
	public List<Individu> findAll() {
		List<Individu> liste = new ArrayList<Individu>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = MonRdvApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Individu> query = em.createQuery("select i from Individu i", Individu.class);

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
	public Optional<Individu> findById(Long id) {
		Optional<Individu> optional = Optional.empty();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = MonRdvApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Individu obj = em.find(Individu.class, id);

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
	public Individu save(Individu obj) {
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

			Query query = em.createQuery("delete from Individu i where i.id = ?1");
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

	@Override
	public List<Patient> findAllPatient() {
		List<Patient> liste = new ArrayList<Patient>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = MonRdvApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

//			TypedQuery<Patient> query = em.createQuery("from Patient", Patient.class);
			
			TypedQuery<Patient> query = em.createQuery("select p from Patient p", Patient.class);

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
	public List<Praticien> findAllPraticien() {
		List<Praticien> liste = new ArrayList<Praticien>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = MonRdvApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Praticien> query = em.createQuery("select p from Praticien p", Praticien.class);

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
	public List<Secretaire> findAllSecretaire() {
		List<Secretaire> liste = new ArrayList<Secretaire>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = MonRdvApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Secretaire> query = em.createQuery("select sec from Secretaire sec", Secretaire.class);

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
	public Optional<Individu> findByIdentifiant(String identifiant) {
		Optional<Individu> optional = Optional.empty();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = MonRdvApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Individu> query = em.createQuery("select i from Individu i where i.utilisateur.identifiant = ?1",
					Individu.class);
			query.setParameter(1, identifiant);

			Individu obj = query.getSingleResult();

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
	public List<Praticien> findAllPraticienBySpecialite(String nom) {
		List<Praticien> liste = new ArrayList<Praticien>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = MonRdvApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Praticien> query = em.createQuery("select p from Praticien p join p.specialites s where s.nom = ?1", Praticien.class);
			query.setParameter(1, nom);

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

}
