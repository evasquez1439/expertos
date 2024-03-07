package rest_accreditation.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;



@Embeddable
public class PatientKey implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Column(name="pacide")//No se pueden campos de tipo idPerson el mapper lo toma como id_person
	private String identification;
	
	
	@Column(name="pactid")//No se pueden campos de tipo idPerson el mapper lo toma como id_person
	private String tipoId;

	public PatientKey() {
		super();

	}
	public PatientKey(String identification, String tipoId) {
		super();
		this.identification = identification;
		this.tipoId = tipoId;
	}
	
	
}
