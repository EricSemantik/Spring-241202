package spring.formation.web.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import jakarta.validation.constraints.NotBlank;
import spring.formation.model.Role;
import spring.formation.model.Utilisateur;

public class UtilisateurDTO {
	private Long id;
	@NotBlank(message = "Identifiant obligatoire")
	private String login;
	private String password;
	private String confirmPassword;
	private boolean active = true;
	private Set<String> roles = new HashSet<>();

	public UtilisateurDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public static UtilisateurDTO from(Utilisateur utilisateur) {
		UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
		BeanUtils.copyProperties(utilisateur, utilisateurDTO);
		utilisateurDTO.setLogin(utilisateur.getIdentifiant());
		utilisateurDTO.setPassword(utilisateur.getMotDePasse());

		utilisateurDTO.setRoles(utilisateur.getRoles().stream().map((r) -> r.name()).collect(Collectors.toSet()));

		return utilisateurDTO;
	}

	public static Utilisateur to(UtilisateurDTO utilisateurDTO) {
		Utilisateur utilisateur = new Utilisateur();
		BeanUtils.copyProperties(utilisateurDTO, utilisateur);
		utilisateur.setIdentifiant(utilisateurDTO.getLogin());
		utilisateur.setMotDePasse(utilisateurDTO.getPassword());

		utilisateur
				.setRoles(utilisateurDTO.getRoles().stream().map((r) -> Role.valueOf(r)).collect(Collectors.toSet()));

		return utilisateur;
	}
}
