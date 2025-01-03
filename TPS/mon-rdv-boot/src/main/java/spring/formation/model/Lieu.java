package spring.formation.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "lieu")
public class Lieu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.ViewBase.class)
	private Long id;
	@Embedded
	@JsonView(Views.ViewLieu.class)
	private Adresse adresse;
	@Column(length = 1000)
	@JsonView(Views.ViewBase.class)
	private String informations;
	@ManyToMany(mappedBy = "lieux")
	@JsonView(Views.ViewLieuDetail.class)
	private List<Praticien> praticiens = new ArrayList<Praticien>();

	public Lieu() {
		super();
	}

	public Lieu(Adresse adresse, String informations) {
		super();
		this.adresse = adresse;
		this.informations = informations;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public String getInformations() {
		return informations;
	}

	public void setInformations(String informations) {
		this.informations = informations;
	}

	public List<Praticien> getPraticiens() {
		return praticiens;
	}

	public void setPraticiens(List<Praticien> praticiens) {
		this.praticiens = praticiens;
	}

}
