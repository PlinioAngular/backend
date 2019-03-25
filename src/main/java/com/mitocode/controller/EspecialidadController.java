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
import com.mitocode.model.Especialidad;
import com.mitocode.service.impl.EspecialidadServiceImpl;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadController {
	
	@Autowired
	private EspecialidadServiceImpl service;
	
	@PostMapping(produces="application/json",consumes="application/json")
	public ResponseEntity<Object> registrar(@Valid @RequestBody Especialidad esp)
	{
		Especialidad especialidad=new Especialidad();
		especialidad=service.registrar(esp);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(especialidad.getIdEspecialidad()).toUri();
		return ResponseEntity.created(location).build();
	}
	@PutMapping(produces="application/json",consumes="application/json")
	public ResponseEntity<Object> modificar(@Valid @RequestBody Especialidad esp)
	{
		service.modificar(esp);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	@GetMapping(produces="application/json")
	public ResponseEntity<List<Especialidad>> listar(){
		return new ResponseEntity<List<Especialidad>>(service.listar(), HttpStatus.OK);
	}
	@GetMapping(value="/{id}",produces="application/json")
	public Resource<Especialidad> listarporid(@PathVariable("id") Integer id){
		Especialidad esp=service.listarid(id);
		if(esp==null) {
			throw new ModeloNotFoundException("Id no encontrado "+id);
		}
		Resource <Especialidad> resource = new Resource<Especialidad>(esp);
		ControllerLinkBuilder linkTo= linkTo(methodOn(this.getClass()).listarporid(id));
		resource.add(linkTo.withRel("especialidad-resource"));
		return resource;
	}
	@DeleteMapping(value="/{id}")
	public void eliminar(@PathVariable("id") Integer id){
		Especialidad esp= service.listarid(id);
		if(esp==null) {
			throw new ModeloNotFoundException("Id no encontrado "+id);
		}else {
			service.eliminar(id);
		}
			
		
	}
}
