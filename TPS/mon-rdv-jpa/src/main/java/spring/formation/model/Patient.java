package spring.formation.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.OneToMany;

public class Patient extends Individu {
	private LocalDate dtNaissance;
	private String lieuNaissance;
	private String numeroSS;
	@OneToMany(mappedBy = "patient")
	private List<Consultation> consultations = new ArrayList<Consultation>();

	public Patient() {
		super();
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
