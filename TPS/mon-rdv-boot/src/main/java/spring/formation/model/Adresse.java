package spring.formation.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Adresse {
	@Column(length = 255)
	@JsonView(Views.ViewBase.class)
	private String rue;
	@Column(length = 255)
	@JsonView(Views.ViewBase.class)
	private String complement;
	@Column(length = 10)
	@JsonView(Views.ViewBase.class)
	private String codePostal;
	@Column(length = 100)
	@JsonView(Views.ViewBase.class)
	private String ville;

	public Adresse() {
		super();
	}

	public Adresse(String rue, String complement, String codePostal, String ville) {
		super();
		this.rue = rue;
		this.complement = complement;
		this.codePostal = codePostal;
		this.ville = ville;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adresse other = (Adresse) obj;
		return Objects.equals(codePostal, other.codePostal) && Objects.equals(complement, other.complement)
				&& Objects.equals(rue, other.rue) && Objects.equals(ville, other.ville);
	}
	
}
