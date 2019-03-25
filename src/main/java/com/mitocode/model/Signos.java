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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="signo")
public class Signos {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY )
	private int idsigno;	
	
	@ManyToOne
	@JoinColumn(name = "id_paciente", nullable=false, foreignKey=@ForeignKey(name="signo_vital_paciente"))
	private Paciente paciente;

	@ApiModelProperty(notes="Nombres debe ser minimo de tres")
	@Size(min=1,message="Nombre debe tener mínimo 1 caracteres")
	@Column(name="temperatura",nullable=false,length=70)
	private String temperatura;
	
	@ApiModelProperty(notes="Nombres debe ser minimo de tres")
	@Size(min=1,message="Nombre debe tener mínimo 1 caracteres")
	@Column(name="ritmo",nullable=false,length=70)
	private String ritmo;
	
	@ApiModelProperty(notes="Nombres debe ser minimo de tres")
	@Size(min=1,message="Nombre debe tener mínimo 1 caracteres")
	@Column(name="pulso",nullable=false,length=70)
	private String pulso;
	
	@JsonSerialize(using=ToStringSerializer.class)
	private LocalDateTime fecha;

	public int getIdsigno() {
		return idsigno;
	}

	public void setIdsigno(int idsigno) {
		this.idsigno = idsigno;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

	public String getRitmo() {
		return ritmo;
	}

	public void setRitmo(String ritmo) {
		this.ritmo = ritmo;
	}

	public String getPulso() {
		return pulso;
	}

	public void setPulso(String pulso) {
		this.pulso = pulso;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	
}
