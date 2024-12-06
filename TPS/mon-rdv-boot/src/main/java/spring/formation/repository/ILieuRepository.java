package spring.formation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.formation.model.Lieu;

public interface ILieuRepository extends JpaRepository<Lieu, Long>{
	@Query("select distinct l from Lieu l left join fetch l.praticiens where l.id = ?1")
	Optional<Lieu> findWithPraticienById(Long id);
}
