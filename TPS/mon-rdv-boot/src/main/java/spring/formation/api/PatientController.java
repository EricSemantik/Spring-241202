package spring.formation.api;

import java.util.List;

import org.springframework.http.HttpStatus;
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

import spring.formation.model.Patient;
import spring.formation.repository.IIndividuRepository;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

	private IIndividuRepository individuRepository;

	@GetMapping("")
	public List<Patient> findAll() {
		return individuRepository.findAllPatient();
	}

	@GetMapping(path = "/{id}")
	public Patient findById(@PathVariable Long id) {
		return (Patient) individuRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Patient create(@RequestBody Patient patient) {
		return individuRepository.save(patient);
	}

	@PutMapping("/{id}")
	public Patient update(@RequestBody Patient patient, @PathVariable Long id) {
		if (id != patient.getId() || !individuRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id incohérent ou inexistant");
		}

		return individuRepository.save(patient);
	}

	@DeleteMapping("/{id}")
	public void remove(@PathVariable Long id) {
		if (!individuRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		individuRepository.deleteById(id);
	}
	
	@PostMapping("/inscription")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Patient inscription(@RequestBody Patient patient) {
		return individuRepository.save(patient);
	}

}
