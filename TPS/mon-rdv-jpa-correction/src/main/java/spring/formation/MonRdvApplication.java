package spring.formation;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MonRdvApplication {
	private static MonRdvApplication instance = null;

	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("mon-rdv-pu");

	private MonRdvApplication() {
		super();
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	

	public static MonRdvApplication getInstance() {
		if (instance == null) {
			instance = new MonRdvApplication();
		}

		return instance;
	}
}
