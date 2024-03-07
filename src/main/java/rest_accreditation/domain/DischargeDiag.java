package rest_accreditation.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="inmdia")
public class DischargeDiag  implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="mdiahis")
	private int hclinica;
	
	@Id
	@Column(name="mdianum")
	private int  accessNumber;
	
	@Column(name="mdiadia")
	private String diagnosis;
	
	@Column(name="mdiatip")
	private String diagnosisType;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumns({
		@JoinColumn(name="mdiahis" , referencedColumnName="pachcehis", insertable = false, updatable = false),
		@JoinColumn(name="mdianum" , referencedColumnName="pachcenum", insertable = false, updatable = false)	
	 }) 
	private InOutEpisodeRel inOutEpisodeRel;

	public int getHclinica() {
		return hclinica;
	}

	public void setHclinica(int hclinica) {
		this.hclinica = hclinica;
	}

	public int getAccessNumber() {
		return accessNumber;
	}

	public void setAccessNumber(int accessNumber) {
		this.accessNumber = accessNumber;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getDiagnosisType() {
		return diagnosisType;
	}

	public void setDiagnosisType(String diagnosisType) {
		this.diagnosisType = diagnosisType;
	}
	
	
	
}
