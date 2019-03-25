package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.dao.IConsultaExamenDAO;
import com.mitocode.model.ConsultaExamen;
import com.mitocode.service.IConsultaExamenService;

@Service
public class ConsultaExamenServiceImpl implements IConsultaExamenService {

	@Autowired
	private IConsultaExamenDAO dao;
	
	@Override
	public ConsultaExamen registrar(ConsultaExamen t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConsultaExamen modificar(ConsultaExamen t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ConsultaExamen> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConsultaExamen listarid(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<ConsultaExamen> listarExamenesPorConsulta(Integer idconsulta) {
		return dao.listarExamenesPorConsulta(idconsulta);
	}

}
