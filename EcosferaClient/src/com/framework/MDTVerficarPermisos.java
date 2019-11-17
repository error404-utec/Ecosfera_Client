package com.framework;



import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Perfil;
import com.entities.Permiso;
import com.entities.Usuario;
import com.services.PermisoBeanRemote;

public class MDTVerficarPermisos {
	private static MDTVerficarPermisos intance =  new MDTVerficarPermisos();
	
	private MDTVerficarPermisos() {
		
	}
	
	public static MDTVerficarPermisos getInstance() {
		return intance;
	}
	
	public boolean validarPermiso(Usuario usuario, String objeto) throws NamingException {
		boolean respuesta = false;
		PermisoBeanRemote permisoBeanRemote  = (PermisoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/PermisoBean!com.services.PermisoBeanRemote");
		List<Permiso> listaPermisos= permisoBeanRemote.obtenerTodos(objeto);
		boolean controlar = false;
		System.out.println("objeto: " + objeto);
		System.out.println("Usuario: " + usuario.getNombre());
		for (Permiso per: listaPermisos) {
			System.out.println(per.getFuncionalidad());
			if(per.getFuncionalidad().equalsIgnoreCase(objeto)) {
				controlar = true;
				break;
			}
		}
		
		if (controlar) {
			for(Perfil perfil: usuario.getPerfiles()) {
				for(Permiso permiso:perfil.getPermisos()) {
					if(permiso.getFuncionalidad().equals(objeto)) {
						respuesta = true;
						break;
					}
				}
			}
		}else{
			respuesta = true;
		}
		return respuesta;
	}
	
}
