package com.mitocode.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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
import com.mitocode.model.Signos;
import com.mitocode.service.impl.SignosServiceImpl;

@RestController
@RequestMapping("/signos")
public class SignoController {
	
	@Autowired
	private SignosServiceImpl service;
	
	@PostMapping(produces="application/json",consumes="application/json")
	public ResponseEntity<Object> registrar(@Valid @RequestBody Signos Sig)
	{
		Signos Signos=new Signos();
		Signos=service.registrar(Sig);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Signos.getIdsigno()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(value="/pageable" , produces="application/json")
	public ResponseEntity<Page<Signos>> listarPageable(Pageable pageable)
	{
		Page<Signos> Signos;
		Signos=service.listarPageable(pageable);
		return new ResponseEntity<Page<Signos>>(Signos,HttpStatus.OK);
	}
	
	@PutMapping(produces="application/json",consumes="application/json")
	public ResponseEntity<Object> modificar(@Valid @RequestBody Signos Sig)
	{
		service.modificar(Sig);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	@GetMapping(produces="application/json")
	public ResponseEntity<List<Signos>> listar(){
		return new ResponseEntity<List<Signos>>(service.listar(), HttpStatus.OK);
	}
	@GetMapping(value="/{id}",produces="application/json")
	public Resource<Signos> listarporid(@PathVariable("id") Integer id){
		Signos Sig=service.listarid(id);
		if(Sig==null) {
			throw new ModeloNotFoundException("Id no encontrado "+id);
		}
		Resource <Signos> resource = new Resource<Signos>(Sig);
		ControllerLinkBuilder linkTo= linkTo(methodOn(this.getClass()).listarporid(id));
		resource.add(linkTo.withRel("Signos-resource"));
		return resource;
	}
	@DeleteMapping(value="/{id}")
	public void eliminar(@PathVariable("id") Integer id){
		Signos Sig= service.listarid(id);
		if(Sig==null) {
			throw new ModeloNotFoundException("Id no encontrado "+id);
		}else {
			service.eliminar(id);
		}
			
		
	}

}
