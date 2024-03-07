package rest_accreditation.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * Entidad que hace referencia la tabla ABPACOTR en la base de datos
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
@Table(name="ABPACOTR")
public class Emergency implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PACOTRSEC")
	private int id;
	
	@Column(name="PACOTRNOR")
	private String nombreAcom;
	
	@Column(name="PACOTRAPR")
	private String apellidoAcom;
	
	@Column(name="PACOTRTER")
	private String telefonoAcom;
	
	@OneToOne
	@JsonBackReference
	@JoinColumn(name="PACOTRSEC" , referencedColumnName="pachis", insertable = false, updatable = false)
	public PatientMortality patient;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreAcom() {
		return nombreAcom;
	}

	public void setNombreAcom(String nombreAcom) {
		this.nombreAcom = nombreAcom;
	}

	public String getApellidoAcom() {
		return apellidoAcom;
	}

	public void setApellidoAcom(String apellidoAcom) {
		this.apellidoAcom = apellidoAcom;
	}

	public String getTelefonoAcom() {
		return telefonoAcom;
	}

	public void setTelefonoAcom(String telefonoAcom) {
		this.telefonoAcom = telefonoAcom;
	}



	
}
