package spring.formation.model;

import java.util.ArrayList;
import java.util.List;

public class Praticien extends Individu {
	private Convention convention;
	private boolean carteVitale;
	private boolean cheque;
	private boolean espece;
	private boolean carteBancaire;
	private List<Lieu> lieux = new ArrayList<Lieu>();
	private List<Specialite> specialites = new ArrayList<Specialite>();
	private List<Motif> motifs = new ArrayList<Motif>();
	private List<Consultation> consultations = new ArrayList<Consultation>();

	public Praticien() {
		super();
	}

	public Convention getConvention() {
		return convention;
	}

	public void setConvention(Convention convention) {
		this.convention = convention;
	}

	public boolean isCarteVitale() {
		return carteVitale;
	}

	public void setCarteVitale(boolean carteVitale) {
		this.carteVitale = carteVitale;
	}

	public boolean isCheque() {
		return cheque;
	}

	public void setCheque(boolean cheque) {
		this.cheque = cheque;
	}

	public boolean isEspece() {
		return espece;
	}

	public void setEspece(boolean espece) {
		this.espece = espece;
	}

	public boolean isCarteBancaire() {
		return carteBancaire;
	}

	public void setCarteBancaire(boolean carteBancaire) {
		this.carteBancaire = carteBancaire;
	}

	public List<Lieu> getLieux() {
		return lieux;
	}

	public void setLieux(List<Lieu> lieux) {
		this.lieux = lieux;
	}

	public List<Specialite> getSpecialites() {
		return specialites;
	}

	public void setSpecialites(List<Specialite> specialites) {
		this.specialites = specialites;
	}

	public List<Motif> getMotifs() {
		return motifs;
	}

	public void setMotifs(List<Motif> motifs) {
		this.motifs = motifs;
	}

	public List<Consultation> getConsultations() {
		return consultations;
	}

	public void setConsultations(List<Consultation> consultations) {
		this.consultations = consultations;
	}

}
