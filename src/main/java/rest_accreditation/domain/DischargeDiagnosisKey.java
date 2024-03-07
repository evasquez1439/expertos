package rest_accreditation.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;



@Embeddable
public class DischargeDiagnosisKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GeneratedValue
	@Column(name="pachcehis")
	private int idhclinica;
	

	@Column(name="pachcenum")
	private int  accessNumber;
	public DischargeDiagnosisKey() {
		super();
	}

	public DischargeDiagnosisKey(int idhclinica, int accessNumber) {
		super();
		this.idhclinica = idhclinica;
		this.accessNumber = accessNumber;
	}
	
	
}
