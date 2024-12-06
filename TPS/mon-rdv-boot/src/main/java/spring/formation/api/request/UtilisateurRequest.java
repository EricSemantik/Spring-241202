package spring.formation.api.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UtilisateurRequest {
	@Size(min = 4, message = "L'identifiant doit comporter au moins 4 caractères")
	private String login;
	@Pattern(regexp = "^\\w{8,}$", message = "Le mot de passe doit être composé d'au moins 8 caractères alphanumériques")
	private String password;
	private String confirmPassword;
	private boolean admin = false;
	private boolean patient = false;
	private boolean praticien = false;
	private boolean secretaire = false;

	public UtilisateurRequest() {
		super();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isPatient() {
		return patient;
	}

	public void setPatient(boolean patient) {
		this.patient = patient;
	}

	public boolean isPraticien() {
		return praticien;
	}

	public void setPraticien(boolean praticien) {
		this.praticien = praticien;
	}

	public boolean isSecretaire() {
		return secretaire;
	}

	public void setSecretaire(boolean secretaire) {
		this.secretaire = secretaire;
	}

}
