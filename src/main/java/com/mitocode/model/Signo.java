package com.mitocode.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Signos Vitales")
@Entity
@Table(name = "signo")
public class Signo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSigno;
	
	@Schema(description = "Temperatura del Paciente")
	@NotNull(message = "Debe de completar la temperatura")
	@Size(min = 0, max = 250, message = "Debe de completar la temperatura")
	@Column(name = "temperatura", length = 250)
	private String temperatura;
	
	@Schema(description = "Pulso del Paciente")
	@NotNull(message = "Debe de completar el pulso")
	@Size(min = 0, max = 250, message = "Debe de completar el pulso")
	@Column(name = "pulso", length = 250)
	private String pulso;
	
	@Schema(description = "Ritmo respiratorio del Paciente")
	@NotNull(message = "Debe de completar el ritmo respiratorio")
	@Size(min = 0, max = 250, message = "Debe de completar el ritmo respiratorio")
	@Column(name = "ritmo", length = 250)
	private String ritmo;
	
	@Schema(description = "Paciente")
	@NotNull(message = "Es necesario seleccionar un paciente")
	@ManyToOne
	@JoinColumn(name = "id_paciente", nullable = false, foreignKey = @ForeignKey(name = "FK_signo_paciente"))
	private Paciente paciente;
	
	@Schema(description = "Fecha")
	@NotNull(message = "Debe de indicar la fecha")
	@Column(name = "fecha", nullable = false)
	private LocalDateTime fecha;

	public Signo() {
		super();
	}

	public Signo(Integer idSigno, String temperatura, String pulso, String ritmo, Paciente paciente,
			LocalDateTime fecha) {
		super();
		this.idSigno = idSigno;
		this.temperatura = temperatura;
		this.pulso = pulso;
		this.ritmo = ritmo;
		this.paciente = paciente;
		this.fecha = fecha;
	}

	public Integer getIdSigno() {
		return idSigno;
	}

	public void setIdSigno(Integer idSigno) {
		this.idSigno = idSigno;
	}

	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

	public String getPulso() {
		return pulso;
	}

	public void setPulso(String pulso) {
		this.pulso = pulso;
	}

	public String getRitmo() {
		return ritmo;
	}

	public void setRitmo(String ritmo) {
		this.ritmo = ritmo;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	

}
