package com.session;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.services.IniciarSesionBeanRemote;


public class IniciarSesion {
	
	public static String iniciar(String nombre,String password) throws NamingException {
		String repuesta = "";
		IniciarSesionBeanRemote iniciarSesionBean  = (IniciarSesionBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/IniciarSesionBean!com.services.IniciarSesionBeanRemote");
		repuesta= iniciarSesionBean.controles_PreInit(nombre, password);
		
		if(repuesta.isEmpty()) {
			Sesion.getInstance();
			IniciarSesionBeanRemote iniciarSesionBean2  = (IniciarSesionBeanRemote)
					InitialContext.doLookup("ECOSFERA_MARK1/IniciarSesionBean!com.services.IniciarSesionBeanRemote");
			Sesion.setUsuario(iniciarSesionBean2.iniciar(nombre, password));
		}
		return repuesta;
	}
}
