package com.ceiba.usuario.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import org.springframework.jdbc.core.RowMapper;

public class MapeoDTOUsuario implements RowMapper<DtoUsuario>, MapperResult {

	@Override
	public DtoUsuario mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		Long id= resultSet.getLong("id");;
		String primerNombre = resultSet.getString("primer_nombre");
		String segundoNombre = resultSet.getString("segundo_nombre");
		String primerApellido = resultSet.getString("primer_apellido");
		String segundoApellido = resultSet.getString("segundo_apellido");
		String tipoDeDocumento = resultSet.getString("tipo_de_documento");
		String documento = resultSet.getString("documento");
		String administradoraSalud = resultSet.getString("administradora_salud");
		LocalDateTime fechaAfiliacionASalud = extraerLocalDateTime(resultSet, "fecha_afiliacion_Salud");
		String administradoraDePension = resultSet.getString("administradora_de_pension");
		LocalDateTime fechaAfiliacionAPension = extraerLocalDateTime(resultSet, "fecha_afiliacion_pension");
		
		return new DtoUsuario(id,primerNombre, segundoNombre, primerApellido, segundoApellido, tipoDeDocumento, documento,
				administradoraSalud, fechaAfiliacionASalud, administradoraDePension, fechaAfiliacionAPension);

	}

}
