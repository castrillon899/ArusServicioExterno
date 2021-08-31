package com.ceiba.usuario.comando.fabrica;

import com.ceiba.usuario.modelo.entidad.Usuario;
import org.springframework.stereotype.Component;

import com.ceiba.usuario.comando.ComandoUsuario;

@Component
public class FabricaUsuario {

	public Usuario crear(ComandoUsuario comandoUsuario) {
		return new Usuario(null, comandoUsuario.getPrimerNombre(), comandoUsuario.getSegundoNombre(),
				comandoUsuario.getPrimerApellido(), comandoUsuario.getSegundoApellido(),
				comandoUsuario.getTipoDeDocumento(), comandoUsuario.getDocumento(),
				comandoUsuario.getAdministradoraSalud(), comandoUsuario.getFechaAfiliacionASalud(),
				comandoUsuario.getAdministradoraDePension(), comandoUsuario.getFechaAfiliacionAPension());
	}

}
