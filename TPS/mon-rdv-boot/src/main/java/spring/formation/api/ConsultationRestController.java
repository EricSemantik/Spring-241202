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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import spring.formation.api.response.ConsultationResponse;
import spring.formation.model.Consultation;
import spring.formation.repository.IConsultationRepository;

@RestController
@RequestMapping("/api/consultation")
public class ConsultationRestController {

	private IConsultationRepository consultationRepo;

	public ConsultationRestController(IConsultationRepository consultationRepo) {
		super();
		this.consultationRepo = consultationRepo;
	}

	@GetMapping("")
	public List<ConsultationResponse> getAll() {
		return this.consultationRepo.findAll().stream().map(ConsultationResponse::convert).toList();
	}

	@GetMapping("/{id}")
	public ConsultationResponse get(@PathVariable Long id) {
		Consultation consultation = this.consultationRepo.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		return ConsultationResponse.convert(consultation);
	}

	@PostMapping("")
	public Consultation post(@RequestBody Consultation consultation) {
		consultation = consultationRepo.save(consultation);

		return consultation;
	}

	@PutMapping("/{id}")
	public Consultation put(@RequestBody Consultation consultation, @PathVariable Long id) {
		if (id != consultation.getId() || !consultationRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le consultation ne peut être mis à jour.");
		}

		return consultationRepo.save(consultation);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!consultationRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Le consultation ne peut être supprimé car inexistant.");
		}

		consultationRepo.deleteById(id);
	}
}
