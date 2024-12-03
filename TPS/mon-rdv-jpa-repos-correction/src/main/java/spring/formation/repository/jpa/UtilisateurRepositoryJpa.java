package spring.formation.repository.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import spring.formation.MonRdvApplication;
import spring.formation.model.Utilisateur;
import spring.formation.repository.IUtilisateurRepository;

public class UtilisateurRepositoryJpa implements IUtilisateurRepository {

	@Override
	public List<Utilisateur> findAll() {
		List<Utilisateur> liste = new ArrayList<Utilisateur>();
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = MonRdvApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
		
			TypedQuery<Utilisateur> query = em.createQuery("select u from Utilisateur u", Utilisateur.class);
			
			liste = query.getResultList();
			
			tx.commit();
		} catch (Exception e) {
			if(tx != null && tx.isActive()) {
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
	public Optional<Utilisateur> findById(Long id) {
		Optional<Utilisateur> optional = Optional.empty();
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = MonRdvApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
		
			TypedQuery<Utilisateur> query = em.createQuery("select u from Utilisateur u where u.id = :monId", Utilisateur.class);
			query.setParameter("monId", id);
			
			Utilisateur obj = query.getSingleResult();
			
//			ou via EntityManager.find() 
//			Utilisateur obj = em.find(Utilisateur.class, id);
			
			optional = Optional.of(obj);
			
			tx.commit();
		} catch (Exception e) {
			if(tx != null && tx.isActive()) {
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
	public Utilisateur save(Utilisateur obj) {
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = MonRdvApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
		
			obj = em.merge(obj);
			
			tx.commit();
		} catch (Exception e) {
			if(tx != null && tx.isActive()) {
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
		
			Query query = em.createQuery("delete from Utilisateur u where u.id = ?1");
			query.setParameter(1, id);
			
			rows = query.executeUpdate();
			
			tx.commit();
		} catch (Exception e) {
			if(tx != null && tx.isActive()) {
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
