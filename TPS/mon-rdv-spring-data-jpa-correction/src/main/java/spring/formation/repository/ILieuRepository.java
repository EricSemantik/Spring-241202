package spring.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.formation.model.Lieu;

public interface ILieuRepository extends JpaRepository<Lieu, Long>{
	
}
