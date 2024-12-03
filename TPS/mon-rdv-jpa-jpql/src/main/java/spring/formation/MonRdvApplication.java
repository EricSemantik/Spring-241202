package spring.formation;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import spring.formation.repository.IConsultationRepository;
import spring.formation.repository.IIndividuRepository;
import spring.formation.repository.ILieuRepository;
import spring.formation.repository.IMotifRepository;
import spring.formation.repository.ISpecialiteRepository;
import spring.formation.repository.IUtilisateurRepository;
import spring.formation.repository.jpa.ConsultationRepositoryJpa;
import spring.formation.repository.jpa.IndividuRepositoryJpa;
import spring.formation.repository.jpa.LieuRepositoryJpa;
import spring.formation.repository.jpa.MotifRepositoryJpa;
import spring.formation.repository.jpa.SpecialiteRepositoryJpa;
import spring.formation.repository.jpa.UtilisateurRepositoryJpa;

public class MonRdvApplication {
	private static MonRdvApplication instance = null;

	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("mon-rdv-pu");

	private final IConsultationRepository consultationRepository = new ConsultationRepositoryJpa();
	private final IIndividuRepository individuRepository = new IndividuRepositoryJpa();
	private final ILieuRepository lieuRepository = new LieuRepositoryJpa();
	private final IMotifRepository motifRepository = new MotifRepositoryJpa();
	private final ISpecialiteRepository specialiteRepository = new SpecialiteRepositoryJpa();
	private final IUtilisateurRepository utilisateurRepository = new UtilisateurRepositoryJpa();

	private MonRdvApplication() {
		super();
	}

	public static MonRdvApplication getInstance() {
		if (instance == null) {
			instance = new MonRdvApplication();
		}

		return instance;
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public IConsultationRepository getConsultationRepository() {
		return consultationRepository;
	}

	public IIndividuRepository getIndividuRepository() {
		return individuRepository;
	}

	public ILieuRepository getLieuRepository() {
		return lieuRepository;
	}

	public IMotifRepository getMotifRepository() {
		return motifRepository;
	}

	public ISpecialiteRepository getSpecialiteRepository() {
		return specialiteRepository;
	}

	public IUtilisateurRepository getUtilisateurRepository() {
		return utilisateurRepository;
	}

}
