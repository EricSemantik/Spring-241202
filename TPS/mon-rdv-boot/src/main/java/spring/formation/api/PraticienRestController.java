package spring.formation.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import spring.formation.model.Praticien;
import spring.formation.repository.IIndividuRepository;

@RestController
@RequestMapping("/api/praticien")
@CrossOrigin("*")
public class PraticienRestController {

	private IIndividuRepository individuRepository;

	public PraticienRestController(IIndividuRepository individuRepository) {
		super();
		this.individuRepository = individuRepository;
	}

	@GetMapping("")
	public List<Praticien> findAll() {
		return individuRepository.findAllPraticien();
	}

	@GetMapping(path = "/{id}")
	public Praticien findById(@PathVariable Long id) {
		return (Praticien) individuRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping(path = "/{id}/with-specialites")
	// JSON avec propriétés standards + les spécialités
	public Praticien findWithSpecialitesById(@PathVariable Long id) {
		return (Praticien) individuRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Praticien create(@RequestBody Praticien praticien) {
		return individuRepository.save(praticien);
	}

	@PutMapping("/{id}")
	public Praticien update(@RequestBody Praticien praticien, @PathVariable Long id) {
		if (id != praticien.getId() || !individuRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id incohérent ou inexistant");
		}

		return individuRepository.save(praticien);
	}

	@DeleteMapping("/{id}")
	public void remove(@PathVariable Long id) {
		if (!individuRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		individuRepository.deleteById(id);
	}
}
