package com.mitocode.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mitocode.model.Paciente;
@Service
public interface IPacienteService extends ICRUD<Paciente>{
	 Page<Paciente>listarPageable(Pageable pageable);

}
