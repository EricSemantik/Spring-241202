package spring.formation.repository;

import java.util.Optional;

import spring.formation.model.Utilisateur;

public interface IUtilisateurRepository extends IRepository<Utilisateur, Long>{
	Optional<Utilisateur> findByIdentifiant(String identifiant);
}
