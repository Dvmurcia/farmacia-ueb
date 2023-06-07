package com.unbosque.bean;

import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;
import org.primefaces.PrimeFaces;

import com.unbosque.entity.Usuario;
import com.unbosque.service.UsuarioService;
import com.unbosque.utils.EncodeSHA256;

@ManagedBean
@SessionScoped
public class LoginMB {
	private final static Logger LOGGER = Logger.getLogger(LoginMB.class.getName());
	private String usuario;
	private String clave;
	private int intentos;

	public void ingresar() {
		UsuarioService usuarioService = new UsuarioService();
		Usuario usuarioLogueado = usuarioService.getUsuario(usuario);
		EncodeSHA256 encoder = new EncodeSHA256();

		try {
			if (encoder.getStringEncoded(clave).equals(usuarioLogueado.getClave())) {
				// TODO switch para redireccione dependiendo al usuario logueado
				switch (usuarioLogueado.getTipoUsuario()) {
				case "":

					break;

				default:
					break;
				}

			} else {
				this.intentos++;
				//showMessage("Credenciales incorrectas");
				LOGGER.info("Credenciales incorrectas para el usuario " + this.usuario);
				// TODO mostrar mensaje de "Error al loguearse"
				// TODO implementar componente de mensajes de PrimeFaces

			}
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("Error al intentar loguearse", e);
		}

		LOGGER.info("Intentos de logueo: " + this.intentos);
	}

	/*
	 public void showMessage(String msj) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje", msj);
		PrimeFaces.current().dialog().showMessageDynamic(message);
	} 
	 */

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
