package spring.formation.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("patient")
public class Patient extends Individu {
	@Column(name = "dt_naissance")
	@JsonView(Views.ViewBase.class)
	private LocalDate dtNaissance;
	@Column(name = "lieu_naissance", length = 100)
	@JsonView(Views.ViewBase.class)
	private String lieuNaissance;
	@Column(name = "numero_ss", length = 15)
	@JsonView(Views.ViewBase.class)
	private String numeroSS;
	@OneToMany(mappedBy = "patient")
	private List<Consultation> consultations = new ArrayList<Consultation>();

	public Patient() {
		super();
	}

	public Patient(Civilite civilite, String nom, String prenom, String email, String telephone) {
		super(civilite, nom, prenom, email, telephone);
	}

	public LocalDate getDtNaissance() {
		return dtNaissance;
	}

	public void setDtNaissance(LocalDate dtNaissance) {
		this.dtNaissance = dtNaissance;
	}

	public String getLieuNaissance() {
		return lieuNaissance;
	}

	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

	public String getNumeroSS() {
		return numeroSS;
	}

	public void setNumeroSS(String numeroSS) {
		this.numeroSS = numeroSS;
	}

	public List<Consultation> getConsultations() {
		return consultations;
	}

	public void setConsultations(List<Consultation> consultations) {
		this.consultations = consultations;
	}

}
