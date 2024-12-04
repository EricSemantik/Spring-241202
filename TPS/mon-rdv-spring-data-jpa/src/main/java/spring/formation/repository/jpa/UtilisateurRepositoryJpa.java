package spring.formation.repository.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import spring.formation.model.Utilisateur;
import spring.formation.repository.IUtilisateurRepository;

@Repository
@Transactional(readOnly = true)
public class UtilisateurRepositoryJpa implements IUtilisateurRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Utilisateur> findAll() {
		TypedQuery<Utilisateur> query = em.createQuery("select u from Utilisateur u", Utilisateur.class);

		return query.getResultList();
	}

	@Override
	public Optional<Utilisateur> findById(Long id) {
		TypedQuery<Utilisateur> query = em.createQuery("select u from Utilisateur u where u.id = :monId",
				Utilisateur.class);
		query.setParameter("monId", id);

		Utilisateur obj = query.getSingleResult();

//			ou via EntityManager.find() 
//			Utilisateur obj = em.find(Utilisateur.class, id);

		if (obj != null)
			return Optional.of(obj);

		return Optional.empty();
	}

	@Override
	@Transactional(readOnly = false)
	public Utilisateur save(Utilisateur obj) {
		return em.merge(obj);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteById(Long id) {
		Query query = em.createQuery("delete from Utilisateur u where u.id = ?1");
		query.setParameter(1, id);

		int rows = query.executeUpdate();

		return rows > 0;
	}

	@Override
	public Optional<Utilisateur> findByIdentifiant(String identifiant) {
		TypedQuery<Utilisateur> query = em.createQuery("select u from Utilisateur u where u.identifiant = ?1",
				Utilisateur.class);
		query.setParameter(1, identifiant);

		Utilisateur obj = query.getSingleResult();

		if (obj != null)
			return Optional.of(obj);

		return Optional.empty();
	}

}
