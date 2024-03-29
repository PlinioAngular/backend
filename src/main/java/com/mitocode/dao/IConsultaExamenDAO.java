package com.mitocode.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.ConsultaExamen;

public interface IConsultaExamenDAO extends JpaRepository<ConsultaExamen, Integer> {
	
	@Modifying
	@Query(value="insert into consulta_examen(id_consulta,id_examen) values(:idConsulta,:idExamen)",nativeQuery=true)
	Integer registrar(@Param("idConsulta")Integer idConsulta, @Param( "idExamen")Integer idExamen);
	
	@Query("FROM ConsultaExamen ce where ce.consulta.idConsulta =:idConsulta")
	List<ConsultaExamen> listarExamenesPorConsulta(@Param("idConsulta") Integer idconsulta);

}
