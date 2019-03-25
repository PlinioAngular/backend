package com.mitocode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.web.bind.annotation.CrossOrigin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Información del paciente")
@Entity
@Table(name="paciente")
@CrossOrigin
public class Paciente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idpaciente;
	@ApiModelProperty(notes="Nombres debe ser minimo de tres")
	@Size(min=3,message="Nombre debe tener mínimo 3 caracteres")
	@Column(name="nombres",nullable=false,length=70)
	private String nombres;
	
	@ApiModelProperty(notes="Nombres debe ser minimo de tres")
	@Size(min=3,message="Nombre debe tener mínimo 3 caracteres")
	@Column(name="apellidos",nullable=false,length=70)
	private String apellidos;
	
	@ApiModelProperty(notes="Nombres debe ser minimo de 8")
	@Size(min=8,max=8,message="Nombre debe tener mínimo 3 caracteres")
	@Column(name="dni",nullable=false,length=8)
	private String dni;
	
	@Size(min=3,message="Nombre debe tener mínimo 3 caracteres")
	@Column(name="direccion",nullable=true,length=150)
	private String direccion;
	
	@Size(min=9,message="Nombre debe tener mínimo 3 caracteres")
	@Column(name="telefono",nullable=true,length=9)	
	private String telefono;
	
	@Column(name="email",nullable=true,length=55)
	private String email;
	
	public void setIdpaciente(Integer idpaciente) {
		this.idpaciente = idpaciente;
	}
	public Integer getIdpaciente() {
		return idpaciente;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
