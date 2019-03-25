package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.dao.IExamenDAO;
import com.mitocode.model.Examen;
import com.mitocode.service.IExamenService;

@Service
public class ExamenServiceImpl implements IExamenService {
	
	@Autowired
	private IExamenDAO dao;

	@Override
	public Examen registrar(Examen t) {
		// TODO Auto-generated method stub
		return dao.save(t);
	}

	@Override
	public Examen modificar(Examen t) {
		// TODO Auto-generated method stub
		return dao.save(t);
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public List<Examen> listar() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Examen listarid(int id) {
		// TODO Auto-generated method stub
		return dao.findOne(id);
	}

}
