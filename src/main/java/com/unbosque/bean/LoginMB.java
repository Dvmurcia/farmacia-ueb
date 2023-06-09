package com.unbosque.bean;

import java.util.Date;
import java.sql.Timestamp;
import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.logging.Logger;
import org.primefaces.PrimeFaces;

import com.unbosque.entity.Usuario;
import com.unbosque.entity.Auditoria;
import com.unbosque.service.AuditoriaService;
import com.unbosque.service.UsuarioService;
import com.unbosque.utils.EncodeSHA256;

@ManagedBean
@SessionScoped
public class LoginMB {
	private final static Logger LOGGER = Logger.getLogger(LoginMB.class.getName());
	private String usuario;
	private String clave;
	private int intentos = 0;
	private Usuario usuarioLogueado;

	public String ingresar() {
		UsuarioService usuarioService = new UsuarioService();
		this.usuarioLogueado = usuarioService.getUsuario(usuario);
		EncodeSHA256 encoder = new EncodeSHA256();
		int limite = 3;

		try {
			if (usuarioLogueado == null) {
				// Poner mensaje que el usuario no existe (PrimeFaces)
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no encontrado",
						"El usuario especificado no existe.");
				PrimeFaces.current().dialog().showMessageDynamic(message);

			} else if (usuarioLogueado != null && usuarioLogueado.getCantIntentos() > limite) {
				// Mensaje de que el usuario esta bloqueado (PrimeFaces)
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario bloqueado",
						"El usuario especificado esta bloqueado.");
				PrimeFaces.current().dialog().showMessageDynamic(message);

			} else if (usuarioLogueado != null
					&& (encoder.getStringEncoded(clave).equals(usuarioLogueado.getClave()))) {
				// TODO switch para redireccione dependiendo al usuario logueado el va a mostar
				// una vista, cajero admis y gestor dueño de inventario
				// Debes dejar el contador de intentos en 0, tanto el atributo this.intentos
				// como en la tabla
				registrarAuditoriaLogin(usuarioLogueado.getLogin());

				switch (usuarioLogueado.getTipoUsuario()) {
				case "A": // Direccionar a la pagina de administrador
					return "Login";

				case "C": // Direccionar a la pagina de cajero
					return "Cajero";

				case "G": // Direccionar a la pagina de gestor
					return "GestorInv";

				default:
					// Mensaje que el usuario no tiene un rol valido
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"El usuario no tiene un rol valido ", "Sin rol el usuario.");
					PrimeFaces.current().dialog().showMessageDynamic(message);

					break;
				}

			} else {
				this.intentos++;
				// showMessage("Credenciales incorrectas");
				LOGGER.info("Credenciales incorrectas para el usuario " + this.usuario);
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Credenciales incorrectas",
						"Credenciales incorrectas.");
				PrimeFaces.current().dialog().showMessageDynamic(message);

				/*
				 * Para hacer esto al usuarioLogueado le seteas el intento en el que va
				 * (usuarioLogueado.setCantIntentos(this.intentos)
				 */
				intentos = 0;
				usuarioLogueado.setCantIntentos(this.intentos);
				/*
				 * Luego usas usuarioService.update(usuarioLogueado) para actualizar el usuario
				 */
				usuarioService.update(usuarioLogueado);

				/*
				 * Actualizar los intentos para el usuario que se esta intentando loguear Debes
				 * usar un metodo que actualice un valor en la base de datos usando el metodo
				 * update de la clase UsuarioService.java la cual recibe el objeto Usuario
				 * COMPLETO
				 */

				// TODO mostrar mensaje de "Error al loguearse"

				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "logueado incorrecto",
						"Error al loguearse");
				PrimeFaces.current().dialog().showMessageDynamic(msg);

				// TODO implementar componente de mensajes de PrimeFaces

			}
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("Error al intentar loguearse", e);
		}

		LOGGER.info("Intentos de logueo: " + this.intentos);
		return null;
	}

	// creo un metodo que se llame registrarAuditoriaLogin
	// y le paso a eso el login del usuario logueado (usuarioLogueado.getLogin)
	public void registrarAuditoriaLogin(String login) {
		AuditoriaService auditoriaService = new AuditoriaService();
		Auditoria auditoria = new Auditoria();
		Date fechaAuditoria = new Date();
		Timestamp timeStamp = new Timestamp(fechaAuditoria.getTime());
		// y empiezo a llenar los datos que me pide la tabla 
		// id valor numerico consecutivo SE GENERA AUTOMATICO

		// id_usuario setearle el usuario
		auditoria.setIdUsuario(login);
		auditoria.setFechaAuditoria(timeStamp); // fecha dateTime
		auditoria.setAccionAuditoria(login); // accion varchar long (L)
		auditoria.setIdTabla(0); // id_tabla null;	
		auditoria.setNombreTabla(null); // nombre_tabla null;

		
		auditoriaService.save(auditoria);
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
