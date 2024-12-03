package spring.formation.repository.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import spring.formation.MonRdvApplication;
import spring.formation.model.Motif;
import spring.formation.repository.IMotifRepository;

public class MotifRepositoryJpa implements IMotifRepository {

	@Override
	public List<Motif> findAll() {
		List<Motif> liste = new ArrayList<Motif>();
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = MonRdvApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
		
			TypedQuery<Motif> query = em.createQuery("select m from Motif m", Motif.class);
			
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
	public Optional<Motif> findById(Long id) {
		Optional<Motif> optional = Optional.empty();
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = MonRdvApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
					
			Motif obj = em.find(Motif.class, id);
			
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
	public Motif save(Motif obj) {
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
		
			Query query = em.createQuery("delete from Motif m where m.id = ?1");
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
