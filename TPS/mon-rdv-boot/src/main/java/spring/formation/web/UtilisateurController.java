package spring.formation.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import spring.formation.model.Role;
import spring.formation.model.Utilisateur;
import spring.formation.repository.IUtilisateurRepository;

@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {

	@Autowired
	private IUtilisateurRepository utilisateurRepo;

	@GetMapping({ "", "/list" }) // ETAPE 1 : Réception de la Request
	public String list(Model model) {
		List<Utilisateur> utilisateurs = utilisateurRepo.findAll(); // ETAPE 2 : Récupérer les données

		model.addAttribute("utilisateurs", utilisateurs); // ETAPE 3 : Renseigner le Model

	return "utilisateur/list"; // ETAPE 4 : Appel de la View (en passant le Model)
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("roles", Role.values());
		model.addAttribute("utilisateur", new Utilisateur());

		return "utilisateur/form";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam("idUtilisateur") Long id, Model model) {
		Optional<Utilisateur> optUtilisateur = utilisateurRepo.findById(id);

		if (optUtilisateur.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		model.addAttribute("roles", Role.values());
		model.addAttribute("utilisateur", optUtilisateur.get());

		return "utilisateur/form";
	}

	@PostMapping("/saveOld")
	public String saveOld(@RequestParam(required = false) Long id, @RequestParam String identifiant,
			@RequestParam(required = false, defaultValue = "") String motDePasse,
			@RequestParam(required = false, defaultValue = "false") boolean active, @RequestParam Role[] roles, Model model) {
		if (id != null && !utilisateurRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatusCode.valueOf(404));
		}
		
		if(roles == null) {
			roles = new Role[0];
		}

		Utilisateur utilisateur = new Utilisateur(identifiant, motDePasse, active, roles);
		utilisateur.setId(id);
		
		utilisateurRepo.save(utilisateur);

		return "redirect:list";
	}

//	@PostMapping("/save")
//	public String save(@ModelAttribute("utilisateur") Utilisateur utilisateur, Model model) {
//		if (utilisateur.getId() != null && !utilisateurRepo.existsById(utilisateur.getId())) {
//			throw new ResponseStatusException(HttpStatusCode.valueOf(404));
//		}
//
//		utilisateurRepo.save(utilisateur);
//
//		return "redirect:list";
//	}

	@GetMapping("/cancel")
	public String cancel() {
		return "forward:list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("idUtilisateur") Long id) {
		if (!utilisateurRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatusCode.valueOf(404));
		}

		utilisateurRepo.deleteById(id);

		return "redirect:list";
	}
}
