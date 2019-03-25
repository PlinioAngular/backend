package com.mitocode.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Paciente;
import com.mitocode.service.impl.PacienteServiceIpml;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
	@Autowired
	private PacienteServiceIpml service;
	
	@PostMapping(produces="application/json",consumes="application/json")
	public ResponseEntity<Object> registrar(@Valid @RequestBody Paciente pac)
	{
		Paciente paciente=new Paciente();
		paciente=service.registrar(pac);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(paciente.getIdpaciente()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(value="/pageable" , produces="application/json")
	public ResponseEntity<Page<Paciente>> listarPageable(Pageable pageable)
	{
		Page<Paciente> pacientes;
		pacientes=service.listarPageable(pageable);
		return new ResponseEntity<Page<Paciente>>(pacientes,HttpStatus.OK);
	}
	
	@PutMapping(produces="application/json",consumes="application/json")
	public ResponseEntity<Object> modificar(@Valid @RequestBody Paciente pac)
	{
		service.modificar(pac);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	@GetMapping(produces="application/json")
	public ResponseEntity<List<Paciente>> listar(){
		return new ResponseEntity<List<Paciente>>(service.listar(), HttpStatus.OK);
	}
	@GetMapping(value="/{id}",produces="application/json")
	public Resource<Paciente> listarporid(@PathVariable("id") Integer id){
		Paciente pac=service.listarid(id);
		if(pac==null) {
			throw new ModeloNotFoundException("Id no encontrado "+id);
		}
		Resource <Paciente> resource = new Resource<Paciente>(pac);
		ControllerLinkBuilder linkTo= linkTo(methodOn(this.getClass()).listarporid(id));
		resource.add(linkTo.withRel("paciente-resource"));
		return resource;
	}
	@DeleteMapping(value="/{id}")
	public void eliminar(@PathVariable("id") Integer id){
		Paciente pac= service.listarid(id);
		if(pac==null) {
			throw new ModeloNotFoundException("Id no encontrado "+id);
		}else {
			service.eliminar(id);
		}
			
		
	}

}
