package spring.formation.api.old;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.formation.model.Utilisateur;
import spring.formation.repository.IUtilisateurRepository;

@Controller
@RequestMapping("/api/old/utilisateur")
public class UtilisateurRestOldController {

	private IUtilisateurRepository utilisateurRepository;

	public UtilisateurRestOldController(IUtilisateurRepository utilisateurRepository) {
		super();
		this.utilisateurRepository = utilisateurRepository;
	}

	@GetMapping("")
	public ResponseEntity<List<Utilisateur>> findAll() {
		return new ResponseEntity<>(utilisateurRepository.findAll(), HttpStatus.OK);
	}

}
