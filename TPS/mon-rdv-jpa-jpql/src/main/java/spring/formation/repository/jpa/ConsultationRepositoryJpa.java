package spring.formation.repository.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import spring.formation.MonRdvApplication;
import spring.formation.model.Consultation;
import spring.formation.repository.IConsultationRepository;

public class ConsultationRepositoryJpa implements IConsultationRepository {

	@Override
	public List<Consultation> findAll() {
		List<Consultation> liste = new ArrayList<Consultation>();
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = MonRdvApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
		
			TypedQuery<Consultation> query = em.createQuery("select c from Consultation c", Consultation.class);
			
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
	public Optional<Consultation> findById(Long id) {
		Optional<Consultation> optional = Optional.empty();
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = MonRdvApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
					
			Consultation obj = em.find(Consultation.class, id);
			
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
	public Consultation save(Consultation obj) {
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
		
			Query query = em.createQuery("delete from Consultation c where c.id = ?1");
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

	@Override
	public List<Consultation> findAllByPraticien(Long idPraticien) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Consultation> findAllByPatient(Long idPatient) {
		// TODO Auto-generated method stub
		return null;
	}

}
