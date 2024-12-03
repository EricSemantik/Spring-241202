package spring.formation.repository.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import spring.formation.MonRdvApplication;
import spring.formation.model.Lieu;
import spring.formation.repository.ILieuRepository;

public class LieuRepositoryJpa implements ILieuRepository {

	@Override
	public List<Lieu> findAll() {
		List<Lieu> liste = new ArrayList<Lieu>();
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = MonRdvApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
		
			TypedQuery<Lieu> query = em.createQuery("select l from Lieu l", Lieu.class);
			
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
	public Optional<Lieu> findById(Long id) {
		Optional<Lieu> optional = Optional.empty();
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = MonRdvApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
					
			Lieu obj = em.find(Lieu.class, id);
			
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
	public Lieu save(Lieu obj) {
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
		
			Query query = em.createQuery("delete from Lieu l where l.id = ?1");
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
