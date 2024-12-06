package spring.formation.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
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

import com.fasterxml.jackson.annotation.JsonView;

import spring.formation.api.request.PatientRequest;
import spring.formation.model.Adresse;
import spring.formation.model.Civilite;
import spring.formation.model.Patient;
import spring.formation.model.Role;
import spring.formation.model.Utilisateur;
import spring.formation.model.Views;
import spring.formation.repository.IIndividuRepository;
import spring.formation.repository.IUtilisateurRepository;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

	private IIndividuRepository individuRepository;
	private IUtilisateurRepository utilisateurRepository;

	public PatientController(IIndividuRepository individuRepository, IUtilisateurRepository utilisateurRepository) {
		super();
		this.individuRepository = individuRepository;
		this.utilisateurRepository = utilisateurRepository;
	}

	@GetMapping("")
	@JsonView(Views.ViewPatient.class)
	public List<Patient> findAll() {
		return individuRepository.findAllPatient();
	}

	@GetMapping(path = "/{id}")
	@JsonView(Views.ViewPatient.class)
	public Patient findById(@PathVariable Long id) {
		return (Patient) individuRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(Views.ViewPatient.class)
	public Patient create(@RequestBody Patient patient) {
		return individuRepository.save(patient);
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewPatient.class)
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
	@JsonView(Views.ViewPatient.class)
	public Patient inscription(@RequestBody PatientRequest patientRequest) {
		Adresse adresse = new Adresse();
		BeanUtils.copyProperties(patientRequest, adresse);

		Utilisateur utilisateur = new Utilisateur();
		BeanUtils.copyProperties(patientRequest, utilisateur);
		utilisateur.setActive(true);
		utilisateur.getRoles().add(Role.PATIENT);

		utilisateur = utilisateurRepository.save(utilisateur);

		Patient patient = new Patient();
		BeanUtils.copyProperties(patientRequest, patient);
		patient.setCivilite(Civilite.valueOf(patientRequest.getCivilite()));
		patient.setDtNaissance(LocalDate.parse(patientRequest.getDtNaissance()));

		patient.setAdresse(adresse);
		patient.setUtilisateur(utilisateur);

		return individuRepository.save(patient);
	}

}
