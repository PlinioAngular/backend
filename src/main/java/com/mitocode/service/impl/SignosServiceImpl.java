package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mitocode.dao.ISignoDAO;
import com.mitocode.model.Paciente;
import com.mitocode.model.Signos;
import com.mitocode.service.ISignosService;

@Service
public class SignosServiceImpl implements ISignosService {

	@Autowired
	private ISignoDAO dao;
	
	@Override
	public Signos registrar(Signos t) {
		// TODO Auto-generated method stub
		return dao.save(t);
	}

	@Override
	public Signos modificar(Signos t) {
		// TODO Auto-generated method stub
		return dao.save(t);
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public List<Signos> listar() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Signos listarid(int id) {
		// TODO Auto-generated method stub
		return dao.findOne(id);
	}
	
	@Override
	public Page<Signos> listarPageable(Pageable pageable) {
		// TODO Auto-generated method stub
		return dao.findAll(pageable);
	}

}
