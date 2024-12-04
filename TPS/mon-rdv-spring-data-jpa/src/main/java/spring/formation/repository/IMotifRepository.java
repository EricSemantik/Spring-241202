package spring.formation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.formation.model.Motif;

public interface IMotifRepository extends JpaRepository<Motif, Long> {
	List<Motif> findByDuree(int duree); // convention de nommage : select m from Motif m where m.duree = ?1
	
	@Query("select m from Motif m where m.praticien.id = :id")
	List<Motif> findAllByPraticienId(@Param("id") Long idPraticien); // par annotation @Query et @Param (si besoin)
	
	@Query("delete from Motif m where m.duree > ?1")
	@Modifying
	void deleteByDureeSup(int duree);
	
	List<Motif> findByTarifSup(double tarif); // par @NamedQuery dans l'entité Motif
}
