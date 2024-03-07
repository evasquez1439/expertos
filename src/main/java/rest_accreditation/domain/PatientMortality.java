package rest_accreditation.domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Entidad que hace referencia la tabla abpac en la base de datos
 * a trav&eacute;s de JPA, tabla de pacientes
 * <br>
 * <b>Anotaciones:</b>
 * <br>
 * <b>{@literal @}Entity:</b> Indica una instacia de clase que puede ser
 * almacenada en la base de datos
 * <br>
 * <b>{@literal @}Table:</b> Hace referencia a la tabla de la cual se va a 
 * manipular informaci&oacute;n de la base de datos
 * <br>
 * @author 71268359
 * @see rest_accreditation.repository.PatientMortalityRepository
 * {@link rest_accreditation.repository.PatientMortalityRepository}
 */
@Entity
@Table(name="abpac")
public class PatientMortality implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="pachis")//No se pueden campos de tipo idPerson el mapper lo toma como id_person
	private int id;
	
	@Column(name="pacide")//No se pueden campos de tipo idPerson el mapper lo toma como id_person
	private String identification;
	
	@Column(name="pactid")//No se pueden campos de tipo idPerson el mapper lo toma como id_person
	private String tipoId;
	
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

	@OneToOne( mappedBy="patient",fetch = FetchType.LAZY)
	@JsonManagedReference
	private Emergency emergency;

	@OneToMany( mappedBy="patient" ,fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set <Episode> episode;
	

	public Emergency getEmergency() {
		return emergency;
	}
	public void setEmergency(Emergency emergency) {
		this.emergency = emergency;
	}
	public Set<Episode> getEpisode() {
		return episode;
	}
	public void setEpisode(Set<Episode> episode) {
		this.episode = episode;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBirthDay() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(birthDay);
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public String getTipoId() {
		return tipoId;
	}
	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
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
	


	@Override
	public String toString() {
		return "Patient [id=" + id + ", identification=" + identification
				+ ", tipoId=" + tipoId + ", name=" + name + ", secondName="
				+ secondName + ", lastName=" + lastName + ", mothersName="
				+ mothersName + ", birthDay=" + birthDay + ", gender=" + gender
				+ ", phone=" + phone + ", secondLastName=" + secondLastName
				+ "]";
	}



}
