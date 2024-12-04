package spring.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.formation.model.Specialite;

public interface ISpecialiteRepository extends JpaRepository<Specialite, Long> {

}
