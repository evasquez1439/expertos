package rest_accreditation.domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * Entidad que hace referencia la tabla cimovane en la base de datos
 * a trav&eacute;s de JPA, tabla de anest&eacute;sia
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
@Table(name="cimovane")
public class Anesthesia implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="movanedoc")
	private Long numCirugia;
	
	public Long getNumCirugia() {
		return numCirugia;
	}

	public void setNumCirugia(Long numCirugia) {
		this.numCirugia = numCirugia;
	}

	@Column(name="movaneane")
	private String codAnestesiologo;
	
	@Column(name="movaneasa")
	private String asa;
	
	@Column(name="movaneind")
	private Date horaIndAnestecia;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="movanedoc" , referencedColumnName="movdoc", insertable = false, updatable = false),
		@JoinColumn(name="movaneead" , referencedColumnName="movead", insertable = false, updatable = false)
	 }) 
	@JsonBackReference
	public SurgeryMovement cirugia;

	public String getCodAnestesiologo() {
		return codAnestesiologo;
	}

	public void setCodAnestesiologo(String codAnestesiologo) {
		this.codAnestesiologo = codAnestesiologo;
	}

	public String getAsa() {
		return asa;
	}

	public void setAsa(String asa) {
		this.asa = asa;
	}

	public String getHoraIndAnestecia() {
		DateFormat df = new SimpleDateFormat("HH:mm:ss a");
		return df.format(horaIndAnestecia);

	}

	public void setHoraIndAnestecia(Date horaIndAnestecia) {
		this.horaIndAnestecia = horaIndAnestecia;
	}
	
	



}
