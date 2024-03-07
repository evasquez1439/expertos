package rest_accreditation.domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;





import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name="hhregcli")
public class ClinicalRegistry  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="regclisec")
	private int id;
	
	@Column(name="regcliepi")
	private int episodio;
	
	
	@Column(name="regclipro")
	private String noteCode;
	
	@Column(name="regclifch")
	private Date noteDate;
	
	@Column(name="regcliusu")
	private String noteUser;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumns({
		@JoinColumn(name="regcliepi" , referencedColumnName="EPIINAEPI", insertable = false, updatable = false)			
	 }) 
	private Episode episode;

//	
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name="regclipro" , referencedColumnName="procod", insertable = false, updatable = false)			
	}) 
	@Where(clause = " procod in ('C010','E047')   ")
	private Program program;
	
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name="regcliubi" , referencedColumnName="ubicod", insertable = false, updatable = false)			
	}) 
	private Ubication ubication;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEpisodio() {
		return episodio;
	}

	public void setEpisodio(int episodio) {
		this.episodio = episodio;
	}

	public String getNoteCode() {
		return noteCode;
	}

	public void setNoteCode(String noteCode) {
		this.noteCode = noteCode;
	}


	public String getNoteDate() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return df.format(noteDate);

	}

	public void setNoteDate(Date noteDate) {
		this.noteDate = noteDate;
	}

	public String getNoteUser() {
		return noteUser;
	}

	public void setNoteUser(String noteUser) {
		this.noteUser = noteUser;
	}

	public Episode getEpisode() {
		return episode;
	}

	public void setEpisode(Episode episode) {
		this.episode = episode;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public Ubication getUbication() {
		return ubication;
	}

	public void setUbication(Ubication ubication) {
		this.ubication = ubication;
	}



}
