package com.ceiba.usuario.comando;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoUsuario{

	 
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
}
