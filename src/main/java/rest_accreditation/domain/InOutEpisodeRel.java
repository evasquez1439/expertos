	package rest_accreditation.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * @author 71268359
 *
 */
@Entity
@Table(name="inpachce")
public class InOutEpisodeRel implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@EmbeddedId
    private DischargeDiagnosisKey dischargeDiagnosisKey;
	
	@Column(name="pachceepi")
	private int episode;

	
	@OneToMany( mappedBy="inOutEpisodeRel" ,fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set <DischargedPatient> dischargedPatient;
	
	@OneToMany( mappedBy="inOutEpisodeRel" ,fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set <DischargeDiag> dischargeDiag;
	
	@OneToMany( mappedBy="inOutEpisodeRel" ,fetch = FetchType.LAZY)
	@JsonManagedReference
	@Where(clause="mesptip='P'")
	private Set <SpecialtyDetail> specialtyDetail;
	
	public int getEpisode() {
		return episode;
	}

	public void setEpisode(int episode) {
		this.episode = episode;
	}

	public Set<DischargedPatient> getDischargedPatient() {
		return dischargedPatient;
	}

	public void setDischargedPatient(Set<DischargedPatient> dischargedPatient) {
		this.dischargedPatient = dischargedPatient;
	}

	public Set<DischargeDiag> getDischargeDiag() {
		return dischargeDiag;
	}

	public void setDischargeDiag(Set<DischargeDiag> dischargeDiag) {
		this.dischargeDiag = dischargeDiag;
	}

	public Set<SpecialtyDetail> getSpecialtyDetail() {
		return specialtyDetail;
	}

	public void setSpecialtyDetail(Set<SpecialtyDetail> specialtyDetail) {
		this.specialtyDetail = specialtyDetail;
	}			

}
