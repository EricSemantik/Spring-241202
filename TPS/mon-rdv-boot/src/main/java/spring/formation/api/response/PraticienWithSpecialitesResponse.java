package spring.formation.api.response;

import java.util.ArrayList;
import java.util.List;

import spring.formation.model.Civilite;
import spring.formation.model.Convention;

public class PraticienWithSpecialitesResponse {
	private Long id;
	private Civilite civilite;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String complement;
	private String codePostal;
	private String ville;
	private Convention convention;
	private boolean carteVitale;
	private boolean cheque;
	private boolean espece;
	private boolean carteBancaire;
	private List<String> specialites = new ArrayList<>();

	public PraticienWithSpecialitesResponse() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Civilite getCivilite() {
		return civilite;
	}

	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
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

	public List<String> getSpecialites() {
		return specialites;
	}

	public void setSpecialites(List<String> specialites) {
		this.specialites = specialites;
	}

}
