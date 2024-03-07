package rest_accreditation.domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

//Paciente
@Entity
@Table(name="abpac")
public class Patient implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="pachis")//No se pueden campos de tipo idPerson el mapper lo toma como id_person
	private double hclinica;
	
	@EmbeddedId
    private PatientKey patientKey;
	
	@Column(name="pacnom")
	private String name;
	
	@Column(name="pacno2")
	private String secondName;
	
	@Column(name="pacap1")
	private String lastName;
	
	@Column(name="pacnma")
	private String mothersName;
	
	@Column(name="pacnac")
	//@DateTimeFormat(pattern = "DD/MM/YYYY")
	private Date birthDay;
	
	@Column(name="pacsex")
	private String gender;
	
	@Column(name="pactel")
	private String phone;
	
	@Column(name="paccoe")
	private String email;
	
	@Column(name="paccel")
	private String cellPhone;

	
	public String getBirthDay() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(birthDay);
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}


	public String getMothersName() {
		return mothersName;
	}
	public void setMothersName(String mothersName) {
		this.mothersName = mothersName;
	}
	public String getFullName(){
		String second=(this.secondName==null  )? "":this.secondName;
		return this.name+" "+second;
	}
	public String getFullLastName(){
		String second=(this.secondLastName==null  )? "":this.secondLastName;
		return this.lastName+" "+second;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecondName() {
		return (secondName==null  )? "":secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSecondLastName() {
		return (secondLastName==null)? "": secondLastName ;
	}

	public void setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
	}

	@Column(name="pacap2")
	private String secondLastName;

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	@Override
	public String toString() {
		return "Patient [hclinica=" + hclinica + ", patientKey=" + patientKey
				+ ", name=" + name + ", secondName=" + secondName
				+ ", lastName=" + lastName + ", mothersName=" + mothersName
				+ ", birthDay=" + birthDay + ", gender=" + gender + ", phone="
				+ phone + ", email=" + email + ", cellPhone=" + cellPhone
				+ ", secondLastName=" + secondLastName + "]";
	}
	




}
