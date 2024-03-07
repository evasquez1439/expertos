package rest_accreditation.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * Entidad que hace referencia la tabla cimovpro en la base de datos
 * a trav&eacute;s de JPA, tabla de detalles de procedimiento
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
@Table(name="cimovpro")
public class ProcedureDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="movprodoc")
	private Long numCirugia;
	
	@Id
	@Column(name="movprolin")
	private Long movprolin;
	
	@Id
	@Column(name="movproead")
	private Long movproead;
	
	@Column(name="movproemp")
	private String tipoResp;
	
	@Column(name="movprocer")
	private String codResp;
	
	@Column(name="movproesp")
	private String codEsp;


	@Column(name="movpromed")
	private String cirujano;
	@Id
	@Column(name="movpropro")
	private String codProcHptu;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="movprodoc" , referencedColumnName="movdoc", insertable = false, updatable = false),
		@JoinColumn(name="movproead" , referencedColumnName="movead", insertable = false, updatable = false)
	 }) 
	@JsonBackReference
	public SurgeryMovement movCirugia;



	public Long getNumCirugia() {
		return numCirugia;
	}

	public void setNumCirugia(Long numCirugia) {
		this.numCirugia = numCirugia;
	}

	public String getTipoResp() {
		return tipoResp;
	}

	public void setTipoResp(String tipoResp) {
		this.tipoResp = tipoResp;
	}

	public String getCodResp() {
		return codResp;
	}

	public void setCodResp(String codResp) {
		this.codResp = codResp;
	}

	public String getCodEsp() {
		return codEsp;
	}

	public void setCodEsp(String codEsp) {
		this.codEsp = codEsp;
	}

	public String getCirujano() {
		return cirujano;
	}

	public void setCirujano(String cirujano) {
		this.cirujano = cirujano;
	}

	public String getCodProcHptu() {
		return codProcHptu;
	}

	public void setCodProcHptu(String codProcHptu) {
		this.codProcHptu = codProcHptu;
	}





}
