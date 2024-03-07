package rest_accreditation.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Entidad que hace referencia la tabla inubi en la base de datos o ubicaci&oacute;n
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
@Table(name="inubi")
public class Ubication implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name="UBICOD")
	private String ubicod;
	

	@Column(name="UBINOM")
	private String name;

	@Column(name="UBIACT")
	private String active;
	
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name="ubiser" , referencedColumnName="sercod", insertable = false, updatable = false)			
	}) 
	private Service service;

	
	
	public Service getService() {
		return service;
	}


	public void setService(Service service) {
		this.service = service;
	}


	public void setUbicod(String ubicod) {
		this.ubicod = ubicod;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getActive() {
		return active;
	}


	public void setActive(String active) {
		this.active = active;
	}






//	@OneToOne( mappedBy="ubication" ,fetch = FetchType.LAZY)
//	private MedicalOrder medicalOrder;

	
}
