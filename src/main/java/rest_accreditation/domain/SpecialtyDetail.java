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
@Table(name="inmesp")
public class SpecialtyDetail implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="mesphis")
	private int hclinica;
	
	@Id
	@Column(name="mespnum")
	private int  accessNumber;
	
	@Column(name="mespesp")
	private String specialty;
	
	@Column(name="mesptip")
	private String specialtyType;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumns({
		@JoinColumn(name="mesphis" , referencedColumnName="pachcehis", insertable = false, updatable = false),
		@JoinColumn(name="mespnum" , referencedColumnName="pachcenum", insertable = false, updatable = false)	
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

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getSpecialtyType() {
		return specialtyType;
	}

	public void setSpecialtyType(String specialtyType) {
		this.specialtyType = specialtyType;
	}
	
	
}
