package spring.formation.model;

import java.util.ArrayList;
import java.util.List;

public class Secretaire extends Individu {
	private List<Praticien> praticiens = new ArrayList<Praticien>();

	public Secretaire() {
		super();
	}

	public List<Praticien> getPraticiens() {
		return praticiens;
	}

	public void setPraticiens(List<Praticien> praticiens) {
		this.praticiens = praticiens;
	}

}
