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

import com.fasterxml.jackson.annotation.JsonView;

import spring.formation.model.Lieu;
import spring.formation.model.Views;
import spring.formation.repository.ILieuRepository;

@RestController
@RequestMapping("/api/lieu")
@CrossOrigin("*")
public class LieuRestController {

	private ILieuRepository lieuRepository;

	public LieuRestController(ILieuRepository lieuRepository) {
		super();
		this.lieuRepository = lieuRepository;
	}
	
	@GetMapping("")
	@JsonView(Views.ViewLieu.class)
	public List<Lieu> findAll() {
		return lieuRepository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	@JsonView(Views.ViewLieuDetail.class)
	public Lieu findById(@PathVariable Long id) {
		return lieuRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(Views.ViewLieu.class)
	public Lieu create(@RequestBody Lieu lieu) {
		return lieuRepository.save(lieu);
	}
	
	@PutMapping("/{id}")
	@JsonView(Views.ViewLieu.class)
	public Lieu update(@RequestBody Lieu lieu, @PathVariable Long id) {
		if(id != lieu.getId() || !lieuRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id incohérent ou inexistant");
		}
		
		return lieuRepository.save(lieu);
	}
		
	@DeleteMapping("/{id}")
	public void remove(@PathVariable Long id) {
		if(!lieuRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		lieuRepository.deleteById(id);
	}
}
