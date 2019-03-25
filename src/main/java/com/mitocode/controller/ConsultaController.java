package com.mitocode.controller;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.dto.ConsultaDTO;
import com.mitocode.dto.ConsultaListaExamenDTO;
import com.mitocode.dto.ConsultaResumenDTO;
import com.mitocode.dto.FiltroConsultaDTO;
import com.mitocode.model.Archivo;
import com.mitocode.model.Consulta;
import com.mitocode.service.IArchivoService;
import com.mitocode.service.impl.ConsultaServiceImpl;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
	
	@Autowired
	private ConsultaServiceImpl service;
	
	@Autowired
	private IArchivoService serviceArchivo;
	
	@PostMapping(produces="application/json",consumes="application/json")
	public ResponseEntity<Object> registrar(@RequestBody ConsultaListaExamenDTO consDTO)
	{
		Consulta consulta=new Consulta();
		consulta=service.registrarTransaccional(consDTO);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(consulta.getIdConsulta()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(produces="application/json")
	public ResponseEntity<List<Consulta>> listar(){
		return new ResponseEntity<List<Consulta>>(service.listar(), HttpStatus.OK);
	}
	
	@GetMapping(value="/hateoas", produces = MediaType.APPLICATION_JSON_VALUE )
	public List<ConsultaDTO> listarHateoas() {
		
		List <Consulta> consultas = new ArrayList<>();
		List <ConsultaDTO> consultaDTO = new ArrayList<>();
		consultas=service.listar();
		for (Consulta c :consultas) {
			ConsultaDTO d=new ConsultaDTO();
			d.setIdConsulta(c.getIdConsulta());
			d.setMedico(c.getMedico());
			d.setPaciente(c.getPaciente());
			
			ControllerLinkBuilder linkTo = linkTo(methodOn(ConsultaController.class).listarPorId((c.getIdConsulta())));
			d.add(linkTo.withSelfRel());
			consultaDTO.add(d);
			
			ControllerLinkBuilder linkto2 = linkTo(methodOn(MedicoController.class).listarporid(c.getMedico().getIdMedico()));
			d.add(linkto2.withSelfRel());
			consultaDTO.add(d);
			
			ControllerLinkBuilder linkto3 = linkTo(methodOn(PacienteController.class).listarporid(c.getPaciente().getIdpaciente()));
			d.add(linkto3.withSelfRel());
			consultaDTO.add(d);
		}
		return consultaDTO;
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public Consulta listarPorId(@PathVariable("id") Integer id) {
		return service.listarid(id);
	}
	
	@PostMapping(value = "/buscar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Consulta>> buscar(@RequestBody FiltroConsultaDTO filtro) {
		List<Consulta> consultas = new ArrayList<>();

		if (filtro != null) {
			if (filtro.getFechaConsulta() != null) {
				consultas = service.buscarFecha(filtro);
			} else {
				consultas = service.buscar(filtro);
			}
		}
		return new ResponseEntity<List<Consulta>>(consultas, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listarResumen", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ConsultaResumenDTO>> listarResumen() {
		List<ConsultaResumenDTO> consultas = new ArrayList<>();
		consultas = service.listarResumen();
		return new ResponseEntity<List<ConsultaResumenDTO>>(consultas, HttpStatus.OK);
	}
	
	@GetMapping(value = "/generarReporte", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> generarReporte() {
		byte[] data = null;
		data = service.generarReporte();
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}
	
	@PostMapping(value = "/guardarArchivo", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Integer> guardarArchivo(@RequestParam("file") MultipartFile file) throws IOException{
		int rpta = 0;
		Archivo ar = new Archivo();
		ar.setFilename(file.getName());
		ar.setValue(file.getBytes());
		rpta = serviceArchivo.guardar(ar);
		
		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
	}
	
	@GetMapping(value = "/leerArchivo/{idArchivo}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> leerArchivo(@PathVariable("idArchivo") Integer idArchivo) throws IOException {
				
		byte[] arr = serviceArchivo.leerArchivo(idArchivo); 

		return new ResponseEntity<byte[]>(arr, HttpStatus.OK);
	}

}
