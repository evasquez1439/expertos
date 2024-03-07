package rest_accreditation.domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author 71268359
 *
 */
@Entity
@Table(name="inmegr")
public class DischargedPatient  implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="egrhis")
	private int hclinica;
	
	@Id
	@Column(name="egrnum")
	private int  accessNumber;
	
	@Column(name="egrdim")
	private String dischargeCause;
		
	
	@Column(name="egregr")
	private Date  dischargeDate;
	
	@Column(name="egrhoe")
	private float dischargeHour;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumns({
		@JoinColumn(name="egrhis" , referencedColumnName="pachcehis", insertable = false, updatable = false),
		@JoinColumn(name="egrnum" , referencedColumnName="pachcenum", insertable = false, updatable = false)	
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

	public String getDischargeCause() {
		return dischargeCause;
	}

	public void setDischargeCause(String dischargeCause) {
		this.dischargeCause = dischargeCause;
	}

	

	public String getDischargeDate() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(dischargeDate);

	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public float getDischargeHour() {
		return dischargeHour;
	}

	public void setDischargeHour(float dischargeHour) {
		this.dischargeHour = dischargeHour;
	}
	
	
	
}
