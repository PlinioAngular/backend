package com.mitocode.dto;

import org.springframework.hateoas.ResourceSupport;

import com.mitocode.model.Medico;
import com.mitocode.model.Paciente;

public class ConsultaDTO extends ResourceSupport {
	
	public int getIdConsulta() {
		return idConsulta;
	}
	public void setIdConsulta(int idConsulta) {
		this.idConsulta = idConsulta;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	private int idConsulta;
	private Paciente paciente;
	private Medico medico;
	
	

}
