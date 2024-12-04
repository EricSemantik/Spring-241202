package spring.formation.repository.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import spring.formation.model.Individu;
import spring.formation.model.Patient;
import spring.formation.model.Praticien;
import spring.formation.model.Secretaire;
import spring.formation.repository.IIndividuRepository;

@Repository
@Transactional(readOnly = true)
public class IndividuRepositoryJpa implements IIndividuRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Individu> findAll() {
		TypedQuery<Individu> query = em.createQuery("select i from Individu i", Individu.class);

		return query.getResultList();
	}

	@Override
	public Optional<Individu> findById(Long id) {
		Individu obj = em.find(Individu.class, id);

		if (obj != null)
			return Optional.of(obj);

		return Optional.empty();
	}

	@Override
	@Transactional(readOnly = false)
	public Individu save(Individu obj) {
		return em.merge(obj);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteById(Long id) {
		Query query = em.createQuery("delete from Individu i where i.id = ?1");
		query.setParameter(1, id);

		int rows = query.executeUpdate();

		return rows > 0;
	}

	@Override
	public List<Patient> findAllPatient() {
		TypedQuery<Patient> query = em.createQuery("select p from Patient p", Patient.class);

		return query.getResultList();
	}

	@Override
	public List<Praticien> findAllPraticien() {
		TypedQuery<Praticien> query = em.createQuery("select p from Praticien p", Praticien.class);

		return query.getResultList();
	}

	@Override
	public List<Secretaire> findAllSecretaire() {
		TypedQuery<Secretaire> query = em.createQuery("select sec from Secretaire sec", Secretaire.class);

		return query.getResultList();
	}

	@Override
	public Optional<Individu> findByIdentifiant(String identifiant) {
		TypedQuery<Individu> query = em.createQuery("select i from Individu i where i.utilisateur.identifiant = ?1",
				Individu.class);
		query.setParameter(1, identifiant);

		Individu obj = query.getSingleResult();

		if (obj != null)
			return Optional.of(obj);

		return Optional.empty();
	}

	@Override
	public List<Praticien> findAllPraticienBySpecialite(String nom) {
		TypedQuery<Praticien> query = em.createQuery("select p from Praticien p join p.specialites s where s.nom = ?1",
				Praticien.class);
		query.setParameter(1, nom);

		return query.getResultList();
	}

}
