package spring.formation.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("praticien")
public class Praticien extends Individu {
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private Convention convention;
	@Column(name = "carte_vitale")
	private boolean carteVitale;
	private boolean cheque;
	private boolean espece;
	@Column(name = "carte_bancaire")
	private boolean carteBancaire;
	@ManyToMany
	@JoinTable(name = "praticien_lieu", joinColumns = @JoinColumn(name = "praticien_id"), inverseJoinColumns = @JoinColumn(name = "lieu_id"))
	private List<Lieu> lieux = new ArrayList<Lieu>();
	@ManyToMany
	@JoinTable(name = "praticien_specialite", joinColumns = @JoinColumn(name = "praticien_id"), inverseJoinColumns = @JoinColumn(name = "specialite_id"))
	private List<Specialite> specialites = new ArrayList<Specialite>();
	@OneToMany(mappedBy = "praticien")
	private List<Motif> motifs = new ArrayList<Motif>();
	@OneToMany(mappedBy = "praticien")
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
