package rest_accreditation.domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * Entidad que hace referencia la tabla cimovpro en la base de datos
 * a trav&eacute;s de JPA, tabla de movimiento de cirug&iacute;a
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
@Table(name="cimov")
public class SurgeryMovement implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="movdoc")//No se pueden campos de tipo idPerson el mapper lo toma como id_person
	private Long numCirugia;
	
	@Id
	@Column(name="movead")//No se pueden campos de tipo idPerson el mapper lo toma como id_person
	private String movead;
	
	
	@Column(name="movnum")
	private Float numIngreso;
	
	@Column(name="movfue")
	private String fuenteCirugia;
	
	@Column(name="movay1")
	private String codAyudante1;
	
	@Column(name="movna1")
	private String nomAyudante1;
	
	@Column(name="movay2")
	private String codAyudante2;
	
	@Column(name="movna2")
	private String nomAyudante2;
	
	@Column(name="movcau")
	private String causaCirUrg;
	
	@Column(name="movcco")
	private String centroCostos;
	
	@Column(name="movcla")
	private String claseHerida;
	
//-----------------------------------
	@Column(name="movrei")
	private String reintervencion;
	
	@Column(name="movcre")
	private String causaReint;
	
	@Column(name="movdes")
	private String destinoSalida;
	
	@Column(name="movdia")
	private String dxPosoperatorio;
	
	@Column(name="movfre")
	private String fueDocIntervAnt;
	
	@Column(name="movdre")
	private Float intervAnt;
	//-----------------------------------------
	@Column(name="movfci")
	private Date fechaCirugia;
	
	@Column(name="movhan")
	private Date horaAplicAntProfil;
	
	@Column(name="movhec")
	private Date horaIniCirugia;
	
	@Column(name="movheq")
	private Date horaPacIngQuir;
	
	@Column(name="movhsc")
	private Date horaFinCirugia;
	
	@Column(name="movhsq")
	private Date horaPacSalQuirofano;
//-----------------------------------
	
	@Column(name="movqui")
	private String quirofano;
	
	@Column(name="movtan")
	private String tipoAnestesia;
	
	@Column(name="movtip")
	private String tipoCirugia;
	
	@Column(name="movtse")
	private String tipoAtencion;
	
	@Column(name="movhis")
	private String hClinic;
	
	@Column(name="movanu")
	private String movanu;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="movhis" , referencedColumnName="pachis", insertable = false, updatable = false)
	private PatientMortality patient;
	
	//Movimiento de dirugía
	@OneToMany( mappedBy="movCirugia" ,fetch = FetchType.LAZY)
	@JsonManagedReference
	public Set <ProcedureDetails> detalleProcedimiento;
	
//	//Movimiento de dirugía
	@OneToOne( mappedBy="cirugia" ,fetch = FetchType.LAZY)
	@JsonManagedReference
	public Anesthesia anesthesia;
	


	public Float getNumIngreso() {
		return numIngreso;
	}

	public Long getNumCirugia() {
		return numCirugia;
	}

	public void setNumCirugia(Long numCirugia) {
		this.numCirugia = numCirugia;
	}

	public void setNumIngreso(Float numIngreso) {
		this.numIngreso = numIngreso;
	}

	public String getFuenteCirugia() {
		return fuenteCirugia;
	}

	public void setFuenteCirugia(String fuenteCirugia) {
		this.fuenteCirugia = fuenteCirugia;
	}

	public String getCodAyudante1() {
		return codAyudante1;
	}

	public void setCodAyudante1(String codAyudante1) {
		this.codAyudante1 = codAyudante1;
	}

	public String getNomAyudante1() {
		return nomAyudante1;
	}

	public void setNomAyudante1(String nomAyudante1) {
		this.nomAyudante1 = nomAyudante1;
	}

	public String getCodAyudante2() {
		return codAyudante2;
	}

	public void setCodAyudante2(String codAyudante2) {
		this.codAyudante2 = codAyudante2;
	}

	public String getNomAyudante2() {
		return nomAyudante2;
	}

	public void setNomAyudante2(String nomAyudante2) {
		this.nomAyudante2 = nomAyudante2;
	}

	public String getCausaCirUrg() {

		return causaCirUrg;
	}

	public void setCausaCirUrg(String causaCirUrg) {
		this.causaCirUrg = causaCirUrg;
	}

	public String getCentroCostos() {
		return centroCostos;
	}

	public void setCentroCostos(String centroCostos) {
		this.centroCostos = centroCostos;
	}

	public String getClaseHerida() {
		return claseHerida;
	}

	public void setClaseHerida(String claseHerida) {
		this.claseHerida = claseHerida;
	}

	public String getReintervencion() {
		return reintervencion;
	}

	public void setReintervencion(String reintervencion) {
		this.reintervencion = reintervencion;
	}

	public String getCausaReint() {
		return causaReint;
	}

	public void setCausaReint(String causaReint) {
		this.causaReint = causaReint;
	}

	public String getDestinoSalida() {
		return destinoSalida;
	}

	public void setDestinoSalida(String destinoSalida) {
		this.destinoSalida = destinoSalida;
	}

	public String getDxPosoperatorio() {
		return dxPosoperatorio;
	}

	public void setDxPosoperatorio(String dxPosoperatorio) {
		this.dxPosoperatorio = dxPosoperatorio;
	}

	public String getFueDocIntervAnt() {
		return fueDocIntervAnt;
	}

	public void setFueDocIntervAnt(String fueDocIntervAnt) {
		this.fueDocIntervAnt = fueDocIntervAnt;
	}

	public Float getIntervAnt() {
		return intervAnt;
	}

	public void setIntervAnt(Float intervAnt) {
		this.intervAnt = intervAnt;
	}

	public String getFechaCirugia() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
		return df.format(fechaCirugia);
	}

	public void setFechaCirugia(Date fechaCirugia) {
		this.fechaCirugia = fechaCirugia;
	}

	public String getHoraAplicAntProfil() {
		DateFormat df = new SimpleDateFormat("HH:mm:ss a");
		return df.format(horaAplicAntProfil);

	}

	public void setHoraAplicAntProfil(Date horaAplicAntProfil) {
		this.horaAplicAntProfil = horaAplicAntProfil;
	}

	public String getHoraIniCirugia() {
		DateFormat df = new SimpleDateFormat("HH:mm:ss a");
		return df.format(horaIniCirugia);

	}

	public void setHoraIniCirugia(Date horaIniCirugia) {
		this.horaIniCirugia = horaIniCirugia;
	}

	public String getHoraPacIngQuir() {
		DateFormat df = new SimpleDateFormat("HH:mm:ss a");
		return df.format(horaPacIngQuir);

	}

	public void setHoraPacIngQuir(Date horaPacIngQuir) {
		this.horaPacIngQuir = horaPacIngQuir;
	}

	public String getHoraFinCirugia() {
		DateFormat df = new SimpleDateFormat("HH:mm:ss a");
		return df.format(horaFinCirugia);
	
	}

	public void setHoraFinCirugia(Date horaFinCirugia) {
		this.horaFinCirugia = horaFinCirugia;
	}

	public String getHoraPacSalQuirofano() {
		DateFormat df = new SimpleDateFormat("HH:mm:ss a");
		return df.format(horaPacSalQuirofano);

	}

	public void setHoraPacSalQuirofano(Date horaPacSalQuirofano) {
		this.horaPacSalQuirofano = horaPacSalQuirofano;
	}

	public String getQuirofano() {
		return quirofano;
	}

	public void setQuirofano(String quirofano) {
		this.quirofano = quirofano;
	}

	public String getTipoAnestesia() {
		return tipoAnestesia;
	}

	public void setTipoAnestesia(String tipoAnestesia) {
		this.tipoAnestesia = tipoAnestesia;
	}

	public String getTipoCirugia() {
		return tipoCirugia;
	}

	public void setTipoCirugia(String tipoCirugia) {
		this.tipoCirugia = tipoCirugia;
	}

	public String getTipoAtencion() {
		return tipoAtencion;
	}

	public void setTipoAtencion(String tipoAtencion) {
		this.tipoAtencion = tipoAtencion;
	}

	public String gethClinic() {
		return hClinic;
	}

	public void sethClinic(String hClinic) {
		this.hClinic = hClinic;
	}

	public String getMovanu() {
		return movanu;
	}

	public void setMovanu(String movanu) {
		this.movanu = movanu;
	}

	
	
}
