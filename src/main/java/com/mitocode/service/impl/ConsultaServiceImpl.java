package com.mitocode.service.impl;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.mitocode.dao.IConsultaDAO;
import com.mitocode.dao.IConsultaExamenDAO;
import com.mitocode.dto.ConsultaListaExamenDTO;
import com.mitocode.dto.ConsultaResumenDTO;
import com.mitocode.dto.FiltroConsultaDTO;
import com.mitocode.model.Consulta;
import com.mitocode.service.IConsultaService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ConsultaServiceImpl implements IConsultaService{
	
	@Autowired
	private IConsultaDAO dao;
	
	@Autowired
	private IConsultaExamenDAO ceDao;

	@Override
	public Consulta registrar(Consulta t) {
		t.getDetalleConsulta().forEach(det->det.setConsulta(t));
		return dao.save(t);
	}

	@Override
	public Consulta modificar(Consulta t) {
		// TODO Auto-generated method stub
		return dao.save(t);
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public List<Consulta> listar() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Consulta listarid(int id) {
		// TODO Auto-generated method stub
		return dao.findOne(id);
	}

	@Transactional
	@Override
	public Consulta registrarTransaccional(ConsultaListaExamenDTO dto) {
		dto.getConsulta().getDetalleConsulta().forEach(det -> det.setConsulta(dto.getConsulta()));
		dao.save(dto.getConsulta());
		dto.getLstExamen().forEach(exa->ceDao.registrar(dto.getConsulta().getIdConsulta(),  exa.getIdExamen()));
		return dto.getConsulta();
	}

	@Override
	public List<Consulta> buscar(FiltroConsultaDTO filtro) {
		// TODO Auto-generated method stub
		return dao.buscar(filtro.getDni(), filtro.getNombreCompleto());
	}

	@Override
	public List<Consulta> buscarFecha(FiltroConsultaDTO filtro) {


		LocalDateTime fechaSgte=filtro.getFechaConsulta().plusDays(1);
		return dao.buscarFecha(filtro.getFechaConsulta(), fechaSgte);
	}
	@Override
	public List<ConsultaResumenDTO> listarResumen() {
		List<ConsultaResumenDTO> consulta = new ArrayList<>();

		//cantidad fecha
		//[1,  10/02/2019]
		//[5,  9/02/2019]
		
		dao.listarResumen().forEach(x -> {
			ConsultaResumenDTO cr = new ConsultaResumenDTO();
			cr.setCantidad(Integer.parseInt(String.valueOf(x[0])));
			cr.setFecha(String.valueOf(x[1]));
			consulta.add(cr);
		});
		return consulta;
	}

	@Override
	public byte[] generarReporte() {
		byte []data=null;
		try {
			File file= new ClassPathResource("/reports/consultas.jasper").getFile();
			JasperPrint print=JasperFillManager.fillReport(file.getPath(), null,new JRBeanCollectionDataSource(this.listarResumen()));
			data = JasperExportManager.exportReportToPdf(print);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	

}
