package com.ceiba.usuario.servicio.testdatabuilder;

import com.ceiba.usuario.modelo.entidad.Usuario;

import java.time.LocalDateTime;

public class UsuarioTestDataBuilder {

    private Long id;
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
	

    public UsuarioTestDataBuilder() {
    	id = 1L;
    	primerNombre = "pepe";
    	segundoNombre = "pepito";
    	primerApellido = "perez";
    	segundoApellido = "Rodriguez";
    	tipoDeDocumento = "CC";
    	documento = "1234";
    	administradoraSalud = "EPS001";
    	fechaAfiliacionASalud = LocalDateTime.now();
    	
    	administradoraDePension = "AFP001";
    	fechaAfiliacionAPension = LocalDateTime.now();
    	
    	
 
    }


    public Usuario build() {

        return  new Usuario(id,primerNombre, segundoNombre, primerApellido, segundoApellido, tipoDeDocumento, documento,
				administradoraSalud, fechaAfiliacionASalud, administradoraDePension, fechaAfiliacionAPension);
    }
}
