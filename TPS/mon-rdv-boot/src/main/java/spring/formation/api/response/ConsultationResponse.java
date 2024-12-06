package spring.formation.api.response;

import org.springframework.beans.BeanUtils;

import spring.formation.model.Consultation;
import spring.formation.model.Statut;

public class ConsultationResponse {
	private Long id;
	private String dtConsultation;
	private int duree;
	private String motif;
	private double tarif;
	private Statut statut;
	private String notes;

	private Long patientId;
	private String patientCivilite;
	private String patientNom;
	private String patientPrenom;
	private String patientEmail;
	private String patientTelephone;

	private Long praticienId;
	private String praticienCivilite;
	private String praticienNom;
	private String praticienPrenom;
	private String praticienEmail;
	private String praticienTelephone;

	private Long lieuId;
	private String lieuInformations;
	private String lieuRue;
	private String lieuComplement;
	private String lieuCodePostal;
	private String lieuVille;

	public ConsultationResponse() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDtConsultation() {
		return dtConsultation;
	}

	public void setDtConsultation(String dtConsultation) {
		this.dtConsultation = dtConsultation;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public double getTarif() {
		return tarif;
	}

	public void setTarif(double tarif) {
		this.tarif = tarif;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getPatientCivilite() {
		return patientCivilite;
	}

	public void setPatientCivilite(String patientCivilite) {
		this.patientCivilite = patientCivilite;
	}

	public String getPatientNom() {
		return patientNom;
	}

	public void setPatientNom(String patientNom) {
		this.patientNom = patientNom;
	}

	public String getPatientPrenom() {
		return patientPrenom;
	}

	public void setPatientPrenom(String patientPrenom) {
		this.patientPrenom = patientPrenom;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public String getPatientTelephone() {
		return patientTelephone;
	}

	public void setPatientTelephone(String patientTelephone) {
		this.patientTelephone = patientTelephone;
	}

	public Long getPraticienId() {
		return praticienId;
	}

	public void setPraticienId(Long praticienId) {
		this.praticienId = praticienId;
	}

	public String getPraticienCivilite() {
		return praticienCivilite;
	}

	public void setPraticienCivilite(String praticienCivilite) {
		this.praticienCivilite = praticienCivilite;
	}

	public String getPraticienNom() {
		return praticienNom;
	}

	public void setPraticienNom(String praticienNom) {
		this.praticienNom = praticienNom;
	}

	public String getPraticienPrenom() {
		return praticienPrenom;
	}

	public void setPraticienPrenom(String praticienPrenom) {
		this.praticienPrenom = praticienPrenom;
	}

	public String getPraticienEmail() {
		return praticienEmail;
	}

	public void setPraticienEmail(String praticienEmail) {
		this.praticienEmail = praticienEmail;
	}

	public String getPraticienTelephone() {
		return praticienTelephone;
	}

	public void setPraticienTelephone(String praticienTelephone) {
		this.praticienTelephone = praticienTelephone;
	}

	public Long getLieuId() {
		return lieuId;
	}

	public void setLieuId(Long lieuId) {
		this.lieuId = lieuId;
	}

	public String getLieuInformations() {
		return lieuInformations;
	}

	public void setLieuInformations(String lieuInformations) {
		this.lieuInformations = lieuInformations;
	}

	public String getLieuRue() {
		return lieuRue;
	}

	public void setLieuRue(String lieuRue) {
		this.lieuRue = lieuRue;
	}

	public String getLieuComplement() {
		return lieuComplement;
	}

	public void setLieuComplement(String lieuComplement) {
		this.lieuComplement = lieuComplement;
	}

	public String getLieuCodePostal() {
		return lieuCodePostal;
	}

	public void setLieuCodePostal(String lieuCodePostal) {
		this.lieuCodePostal = lieuCodePostal;
	}

	public String getLieuVille() {
		return lieuVille;
	}

	public void setLieuVille(String lieuVille) {
		this.lieuVille = lieuVille;
	}
	
	public static ConsultationResponse convert(Consultation consult) {
		ConsultationResponse consultationResponse = new ConsultationResponse();

		BeanUtils.copyProperties(consult, consultationResponse);
		
		consultationResponse.setDtConsultation(consult.getDtConsultation().toString());

		if (consult.getPatient() != null) {
			consultationResponse.setPatientId(consult.getPatient().getId());
			consultationResponse.setPatientCivilite(consult.getPatient().getCivilite().name());
			consultationResponse.setPatientNom(consult.getPatient().getNom());
			consultationResponse.setPatientPrenom(consult.getPatient().getPrenom());
			consultationResponse.setPatientEmail(consult.getPatient().getEmail());
			consultationResponse.setPatientTelephone(consult.getPatient().getTelephone());
		}

		if (consult.getPraticien() != null) {
			consultationResponse.setPraticienId(consult.getPraticien().getId());
			consultationResponse.setPraticienCivilite(consult.getPraticien().getCivilite().name());
			consultationResponse.setPraticienNom(consult.getPraticien().getNom());
			consultationResponse.setPraticienPrenom(consult.getPraticien().getPrenom());
			consultationResponse.setPraticienEmail(consult.getPraticien().getEmail());
			consultationResponse.setPraticienTelephone(consult.getPraticien().getTelephone());
		}

		if (consult.getLieu() != null) {
			consultationResponse.setLieuId(consult.getLieu().getId());
			consultationResponse.setLieuInformations(consult.getLieu().getInformations());
			if (consult.getLieu().getAdresse() != null) {
				consultationResponse.setLieuRue(consult.getLieu().getAdresse().getRue());
				consultationResponse.setLieuComplement(consult.getLieu().getAdresse().getComplement());
				consultationResponse.setLieuCodePostal(consult.getLieu().getAdresse().getCodePostal());
				consultationResponse.setLieuVille(consult.getLieu().getAdresse().getVille());
			}
		}

		return consultationResponse;
	}

}
