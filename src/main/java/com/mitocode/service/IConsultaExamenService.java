package com.mitocode.service;

import java.util.List;

import com.mitocode.model.ConsultaExamen;

public interface IConsultaExamenService extends ICRUD<ConsultaExamen> {
	
	List<ConsultaExamen> listarExamenesPorConsulta(Integer idconsulta);

}
