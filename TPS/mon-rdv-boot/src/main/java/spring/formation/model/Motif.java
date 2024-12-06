package spring.formation.model;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "motif")
@NamedQuery(name = "Motif.findByTarifSup", query = "select m from Motif m where m.tarif > ?1")
public class Motif {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.ViewBase.class)
	private Long id;
	@Column(length = 100)
	@JsonView(Views.ViewBase.class)
	private String libelle;
	@JsonView(Views.ViewBase.class)
	private int duree;
	@JsonView(Views.ViewBase.class)
	private Double tarif;
	@ManyToOne
	@JoinColumn(name = "praticien_id")
	@JsonView(Views.ViewMotif.class)
	private Praticien praticien;

	public Motif() {
		super();
	}

	public Motif(String libelle, int duree, Double tarif, Praticien praticien) {
		super();
		this.libelle = libelle;
		this.duree = duree;
		this.tarif = tarif;
		this.praticien = praticien;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public Double getTarif() {
		return tarif;
	}

	public void setTarif(Double tarif) {
		this.tarif = tarif;
	}

	public Praticien getPraticien() {
		return praticien;
	}

	public void setPraticien(Praticien praticien) {
		this.praticien = praticien;
	}

}
