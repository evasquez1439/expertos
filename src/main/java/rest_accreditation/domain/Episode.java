package rest_accreditation.domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Entidad que hace referencia la tabla HIEPIINA en la base de datos
 * a trav&eacute;s de JPA
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
 */

@Entity
@Table(name="HIEPIINA")
public class Episode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="EPIINAEPI")
	private int id;
	
	@Column(name="EPIINAHIS")//Esta es la historia clínica 
	private int hclinica;
	
	@Column(name="EPIINAHAB")//Esta es la historia clínica 
	private String cama;
	
	@Column(name="EPIINAFEN")//Esta es la historia clínica 
	private Date fechHoraIngreso;
	
	@Column(name="EPIINAFAL")//Esta es la historia clínica 
	private Date fechaHoraAlta;
	

	
	//Orden médica
	@ManyToOne
	@JsonBackReference
	@JoinColumns({
		@JoinColumn(name="EPIINAHIS" , referencedColumnName="PACHIS", insertable = false, updatable = false)			
	 }) 
	private PatientMortality patient;
	
	@OneToMany( mappedBy="episode")
	@JsonManagedReference
	@Where(clause = " regclipro in ('C010','E047')   ")
	private Set <ClinicalRegistry> clinicalRegistry;

	
	
	public Set<ClinicalRegistry> getClinicalRegistry() {
		return clinicalRegistry;
	}


	public void setClinicalRegistry(Set<ClinicalRegistry> clinicalRegistry) {
		this.clinicalRegistry = clinicalRegistry;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getHclinica() {
		return hclinica;
	}


	public void setHclinica(int hclinica) {
		this.hclinica = hclinica;
	}


	public String getCama() {
		return cama;
	}


	public void setCama(String cama) {
		this.cama = cama;
	}


	public PatientMortality getPatient() {
		return patient;
	}


	public void setPatient(PatientMortality patient) {
		this.patient = patient;
	}

	

	public String getFechHoraIngreso() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return df.format(fechHoraIngreso);

	}


	public void setFechHoraIngreso(Date fechHoraIngreso) {
		this.fechHoraIngreso = fechHoraIngreso;
	}




	public String getFechaHoraAlta() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return df.format(fechaHoraAlta);

	}


	public void setFechaHoraAlta(Date fechaHoraAlta) {
		this.fechaHoraAlta = fechaHoraAlta;
	}



}
