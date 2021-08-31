package com.ceiba.usuario.servicio.testdatabuilder;

import com.ceiba.usuario.comando.ComandoUsuario;
import java.time.LocalDateTime;

public class ComandoUsuarioTestDataBuilder {

	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String tipoDeDocumento;
	private String documento;
	private String administradoraSalud;
	private LocalDateTime fechaAfiliacionASalud;
	private String administradoraDePension;
	private LocalDateTime fechaAfiliacionAPension;

	public ComandoUsuarioTestDataBuilder() {
		primerNombre = "ANGEL";
		segundoNombre = "DAVID";
		primerApellido = "Marin";
		segundoApellido = "Castrillon";
		tipoDeDocumento = "CC";
		documento = "1223";
		administradoraSalud = "EPS001";
		fechaAfiliacionASalud = LocalDateTime.now();
		administradoraDePension = "AFP001";
		fechaAfiliacionAPension = LocalDateTime.now();
	}

	public ComandoUsuario build() {
		return new ComandoUsuario
		(primerNombre, segundoNombre, primerApellido, segundoApellido, tipoDeDocumento, documento, administradoraSalud,
				fechaAfiliacionASalud, administradoraDePension, fechaAfiliacionAPension);

	}
}
