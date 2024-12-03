package spring.formation.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import spring.formation.MonRdvApplication;

public class MainJpa {

	public static void main(String[] args) {
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = MonRdvApplication.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			

			
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
	

		MonRdvApplication.getInstance().getEmf().close();
	}

}
