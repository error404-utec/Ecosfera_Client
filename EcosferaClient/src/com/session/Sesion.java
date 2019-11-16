package com.session;

import com.entities.Usuario;

public class Sesion {
	//instancia de la clase
	private static Sesion instancia = new Sesion(); 
	private static Usuario usuario;

	private Sesion() {	
	} 
	

	public static Sesion getInstance() { 
		return instancia; 
	}


	public static Usuario getUsuario() {
		return usuario;
	}


	public static void setUsuario(Usuario usuario) {
		Sesion.usuario = usuario;
	}
	
	

}
