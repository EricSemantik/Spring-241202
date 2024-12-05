package spring.formation.api;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import spring.formation.model.Utilisateur;
import spring.formation.repository.IUtilisateurRepository;

@RestController
@RequestMapping("/api/utilisateur")
@CrossOrigin("*")
public class UtilisateurRestController {

	private IUtilisateurRepository utilisateurRepository;

	public UtilisateurRestController(IUtilisateurRepository utilisateurRepository) {
		super();
		this.utilisateurRepository = utilisateurRepository;
	}
	
	@GetMapping("")
	public List<Utilisateur> findAll() {
		return utilisateurRepository.findAll();
	}
	
	@GetMapping(path = "/{id}", produces = {"application/json", "application/xml"})
	public Utilisateur findById(@PathVariable Long id) {
		return utilisateurRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping("")
	public Utilisateur create(@RequestBody Utilisateur utilisateur) {
		return utilisateurRepository.save(utilisateur);
	}
	
	@PutMapping("/{id}")
	public Utilisateur update(@RequestBody Utilisateur utilisateur, @PathVariable Long id) {
		if(id != utilisateur.getId() || !utilisateurRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id incohérent ou inexistant");
		}
		
		return utilisateurRepository.save(utilisateur);
	}
	
	@PatchMapping("/{id}")
	public Utilisateur partialUpdate(@RequestBody Map<String, Object> fields, @PathVariable Long id) {
		Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "id inexistant"));
		
		if(fields.containsKey("identifiant")) {
			utilisateur.setIdentifiant((String) fields.get("identifiant"));
		}
		if(fields.containsKey("motDePasse")) {
			utilisateur.setMotDePasse((String) fields.get("motDePasse"));
		}
		
		return utilisateurRepository.save(utilisateur);
	}
	
	@DeleteMapping("/{id}")
	public void remove(@PathVariable Long id) {
		if(!utilisateurRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		utilisateurRepository.deleteById(id);
	}
}