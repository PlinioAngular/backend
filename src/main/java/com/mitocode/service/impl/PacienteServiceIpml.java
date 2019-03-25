package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mitocode.dao.IPacienteDAO;
import com.mitocode.model.Paciente;
import com.mitocode.service.IPacienteService;
@Service
public class PacienteServiceIpml implements IPacienteService{
	@Autowired
	private IPacienteDAO dao;

	@Override
	public Paciente registrar(Paciente t) {
		// TODO Auto-generated method stub
		return dao.save(t);
	}

	@Override
	public Paciente modificar(Paciente t) {
		// TODO Auto-generated method stub
		return dao.save(t);
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public List<Paciente> listar() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Paciente listarid(int id) {
		// TODO Auto-generated method stub
		return dao.findOne(id);
	}

	@Override
	public Page<Paciente> listarPageable(Pageable pageable) {
		// TODO Auto-generated method stub
		return dao.findAll(pageable);
	}

}
