package spring.formation.model;

public enum Role {
	PATIENT, PRATICIEN, SECRETAIRE, ADMIN;

	public String authority() {
		return "ROLE_" + name();
	}
}
