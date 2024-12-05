package spring.formation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import spring.formation.model.Utilisateur;

public interface IUtilisateurRepository extends JpaRepository<Utilisateur, Long>{
	@RestResource(path = "by-identifiant")
	Optional<Utilisateur> findByIdentifiant(@Param("login") String identifiant);
}
