package com.unbosque.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.unbosque.entity.Usuario;
import com.unbosque.service.UsuarioService;

@ManagedBean
@SessionScoped
public class LoginMB {
	private String usuario;
	private String clave;
	private int intentos;

	public void ingresar() {
		UsuarioService usuarioService = new UsuarioService();
		Usuario usuarioLogueado = usuarioService.getUsuario(usuario);

		if (clave.equals(usuarioLogueado.getClave())) {
			// TODO switch para redireccione dependiendo al usuario logueado
			
		} else {
			this.intentos++;
			// TODO mostrar mensaje de "Error al loguearse"
			// TODO implementar componente de mensajes de PrimeFaces

		}
		
		System.out.println("Intentos de logueo: " + this.intentos);
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public int getIntentos() {
		return intentos;
	}

	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}

}
