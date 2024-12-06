package spring.formation.api;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
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

import jakarta.validation.Valid;
import spring.formation.api.request.ConnexionRequest;
import spring.formation.api.request.UtilisateurRequest;
import spring.formation.api.response.ConnexionResponse;
import spring.formation.config.jwt.JWTUtils;
import spring.formation.model.Role;
import spring.formation.model.Utilisateur;
import spring.formation.repository.IUtilisateurRepository;

@RestController
@RequestMapping("/api/utilisateur")
@CrossOrigin("*")
public class UtilisateurRestController {

	private IUtilisateurRepository utilisateurRepository;

	// Par défaut, ce manager n'existe pas dans le contexte, donc on le configure
	// dans SecurityConfig
	private AuthenticationManager authenticationManager;

	public UtilisateurRestController(IUtilisateurRepository utilisateurRepository,
			AuthenticationManager authenticationManager) {
		super();
		this.utilisateurRepository = utilisateurRepository;
		this.authenticationManager = authenticationManager;
	}

	@GetMapping("")
	public List<Utilisateur> findAll() {
		return utilisateurRepository.findAll();
	}

	@GetMapping(path = "/{id}", produces = { "application/json", "application/xml" })
	public Utilisateur findById(@PathVariable Long id) {
		return utilisateurRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping("")
	public ResponseEntity<Utilisateur> create(@RequestBody @Valid UtilisateurRequest utilisateurRequest,
			BindingResult result) {
		if (utilisateurRequest.getPassword() != null
				&& !utilisateurRequest.getPassword().equals(utilisateurRequest.getConfirmPassword())) {
			result.rejectValue("confirmPassword", "utilisateur.confirPassword",
					"La confirmation du mot de passe est incorrecte");
		}

		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utilisateur invalide !!!");
		}

		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setIdentifiant(utilisateurRequest.getLogin());
		utilisateur.setMotDePasse(utilisateurRequest.getPassword());
		utilisateur.setActive(true);

		if (utilisateurRequest.isAdmin()) {
			utilisateur.getRoles().add(Role.ADMIN);
		}
		if (utilisateurRequest.isPatient()) {
			utilisateur.getRoles().add(Role.PATIENT);
		}
		if (utilisateurRequest.isPraticien()) {
			utilisateur.getRoles().add(Role.PRATICIEN);
		}
		if (utilisateurRequest.isSecretaire()) {
			utilisateur.getRoles().add(Role.SECRETAIRE);
		}

		return new ResponseEntity<>(utilisateurRepository.save(utilisateur), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public Utilisateur update(@RequestBody Utilisateur utilisateur, @PathVariable Long id) {
		if (id != utilisateur.getId() || !utilisateurRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id incohérent ou inexistant");
		}

		return utilisateurRepository.save(utilisateur);
	}

	@PatchMapping("/{id}")
	public Utilisateur partialUpdate(@RequestBody Map<String, Object> fields, @PathVariable Long id) {
		Utilisateur utilisateur = utilisateurRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "id inexistant"));

		if (fields.containsKey("identifiant")) {
			utilisateur.setIdentifiant((String) fields.get("identifiant"));
		}
		if (fields.containsKey("motDePasse")) {
			utilisateur.setMotDePasse((String) fields.get("motDePasse"));
		}

		return utilisateurRepository.save(utilisateur);
	}

	@DeleteMapping("/{id}")
	public void remove(@PathVariable Long id) {
		if (!utilisateurRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		utilisateurRepository.deleteById(id);
	}

	@PostMapping("/connexion")
	public ConnexionResponse connexion(@RequestBody ConnexionRequest connexionRequest) {
		// On va demander à SPRING SECURITY de vérifier le username / password
		// On a besoin d'un AuthenticationManager
		// On utilisera la méthode authenticate, qui attend un Authentication
		// Et on utilisera le type UsernamePasswordAuthenticationToken pour donner le
		// username & le password
		Authentication authentication = new UsernamePasswordAuthenticationToken(connexionRequest.getUsername(),
				connexionRequest.getPassword());

		// On demande à SPRING SECURITY de vérifier ces informations de connexion
		this.authenticationManager.authenticate(authentication);

		// Si on arrive ici, c'est que la connexion a fonctionné
		ConnexionResponse response = new ConnexionResponse();

		// On génère un jeton pour l'utilisateur connecté
		String token = JWTUtils.generate(authentication);

		response.setSuccess(true);
		response.setToken(token); // On donne le jeton en réponse

		return response;
	}
}
