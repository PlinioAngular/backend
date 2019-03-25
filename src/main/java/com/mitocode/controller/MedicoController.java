package com.mitocode.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.mitocode.model.Medico;
import com.mitocode.service.impl.MedicoServiceImpl;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private MedicoServiceImpl service;
	
	@PostMapping(produces="application/json",consumes="application/json")
	public ResponseEntity<Object> registrar(@Valid @RequestBody Medico med)
	{
		Medico medico=new Medico();
		medico=service.registrar(med);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(medico.getIdMedico()).toUri();
		return ResponseEntity.created(location).build();
	}
	@PutMapping(produces="application/json",consumes="application/json")
	public ResponseEntity<Object> modificar(@Valid @RequestBody Medico med)
	{
		service.modificar(med);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	@GetMapping(produces="application/json")
	public ResponseEntity<List<Medico>> listar(){
		return new ResponseEntity<List<Medico>>(service.listar(), HttpStatus.OK);
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value="/{id}",produces="application/json")
	public Resource<Medico> listarporid(@PathVariable("id") Integer id){
		Medico med=service.listarid(id);
		if(med==null) {
			throw new ModeloNotFoundException("Id no encontrado "+id);
		}
		Resource <Medico> resource = new Resource<Medico>(med);
		ControllerLinkBuilder linkTo= linkTo(methodOn(this.getClass()).listarporid(id));
		resource.add(linkTo.withRel("medico-resource"));
		return resource;
	}
	@DeleteMapping(value="/{id}")
	public void eliminar(@PathVariable("id") Integer id){
		Medico med= service.listarid(id);
		if(med==null) {
			throw new ModeloNotFoundException("Id no encontrado "+id);
		}else {
			service.eliminar(id);
		}
			
		
	}

}
