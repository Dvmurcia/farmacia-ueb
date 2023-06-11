package com.unbosque.bean;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.CellEditEvent;

import com.google.protobuf.TextFormat.ParseException;
import com.unbosque.entity.Usuario;
import com.unbosque.service.UsuarioService;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@ManagedBean
@SessionScoped
public class UsuarioMB {

	private String login;
	private String apellidoUsuario;
	private int cantIntentos;
	private String clave;
	private String correoUsuario;
	private byte estadoUsusario;
	private Date fechaRegistro;
	private Date fechaUltimaClave;
	private String nombreUsuario;
	private String tipoUsuario;
	
	private Usuario usuario;
	private List<Usuario> listaUsuarios;
	private Usuario usuarioSeleccionado;

	@Inject
	private UsuarioService usuarioService;

	@PostConstruct
	private void init() {
		this.usuarioService = new UsuarioService();
		this.listaUsuarios = getListarUsuarios();
		
	}

	public void crearUsuario() {
		System.out.println("creando usuario...");
		Usuario nuevoUsuario = new Usuario();

		nuevoUsuario.setApellidoUsuario(apellidoUsuario);
		nuevoUsuario.setCantIntentos(cantIntentos);
		nuevoUsuario.setClave(clave);
		nuevoUsuario.setCorreoUsuario(correoUsuario);
		nuevoUsuario.setCorreoUsuario(correoUsuario);
		nuevoUsuario.setEstadoUsusario(estadoUsusario);
		nuevoUsuario.setFechaRegistro(fechaRegistro);
		nuevoUsuario.setFechaUtlimaClave(fechaRegistro);
		nuevoUsuario.setNombreUsuario(nombreUsuario);
		nuevoUsuario.setTipoUsuario(tipoUsuario);

		this.usuarioService.save(nuevoUsuario);
		System.out.println("Usuario creado");
		this.listaUsuarios = this.usuarioService.list();

		this.apellidoUsuario = null;
		this.cantIntentos = 0;
		this.clave = null;
		this.correoUsuario = null;
		this.estadoUsusario = 0;
		this.fechaRegistro = null;
		this.fechaUltimaClave = null;
		this.nombreUsuario = null;
		this.tipoUsuario = null;

		PrimeFaces.current().executeScript("PF(´nuevoUsuario´).hide();");
		PrimeFaces.current().ajax().update(":form:usuarios");

	}

	public void reset() {
		PrimeFaces.current().resetInputs(":dialogNuevoUsuario:usuarioGrid");
	}

	public List<Usuario> getListarUsuarios() {
		UsuarioService usuarioService = new UsuarioService();
		return usuarioService.list();
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void editarMsj(RowEditEvent<Usuario> event) {
		FacesMessage msj = new FacesMessage("Usuario Edited", String.valueOf(((Usuario) event.getObject()).getLogin()));
		FacesContext.getCurrentInstance().addMessage("ID:", msj);
	}

	public void editarUsuario(RowEditEvent<Usuario> event) {
		System.out.println("Editar usuario: " + event.getObject().getLogin());
		Usuario usuarioEditado = event.getObject();
		System.out.println("usuario" + usuarioEditado);
		usuarioService.update(usuarioEditado);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario editado"));
	}

	public void onRowCancel(RowEditEvent<Usuario> event) {
		FacesMessage msj = new FacesMessage("Se cancelo la edicion del usuario");
		FacesContext.getCurrentInstance().addMessage(null, msj);
	}

	public void eliminarUsuario() {
		Integer estado = 0;
		System.out.println("Cambiando estado: " + this.usuarioSeleccionado);
		this.usuarioSeleccionado.setEstadoUsusario(estado.byteValue());
		this.usuarioService.update(this.usuarioSeleccionado);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario eliminado"));
		PrimeFaces.current().ajax().update("form:msjs", "form:usuarios");
	}

	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}

	public void showInfo() {
		addMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Operación exitosa");
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getApellidoUsuario() {
		return apellidoUsuario;
	}

	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}

	public int getCantIntentos() {
		return cantIntentos;
	}

	public void setCantIntentos(int cantIntentos) {
		this.cantIntentos = cantIntentos;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCorreoUsuario() {
		return correoUsuario;
	}

	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}

	public byte getEstadoUsusario() {
		return estadoUsusario;
	}

	public void setEstadoUsusario(byte estadoUsuario) {
		this.estadoUsusario = estadoUsuario;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistroS(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaUltimaClave() {
		return fechaUltimaClave;
	}

	public void setFechaUltimaClave(Date fechaUltimaClave) {
		this.fechaUltimaClave = fechaUltimaClave;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

}
