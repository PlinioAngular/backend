package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.dao.IMedicoDAO;
import com.mitocode.model.Medico;
import com.mitocode.service.IMedicoService;

@Service
public class MedicoServiceImpl implements IMedicoService {

	@Autowired
	private IMedicoDAO dao;
	
	@Override
	public Medico registrar(Medico t) {
		// TODO Auto-generated method stub
		return dao.save(t);
	}

	@Override
	public Medico modificar(Medico t) {
		// TODO Auto-generated method stub
		return dao.save(t);
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public List<Medico> listar() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Medico listarid(int id) {
		// TODO Auto-generated method stub
		return dao.findOne(id);
	}

}
