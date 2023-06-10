package com.unbosque.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "login")
	private String login;

	@Column(name="apellido_usuario")
	private String apellidoUsuario;

	@Column(name="cant_intentos")
	private int cantIntentos;
	
	@Column(name="clave")
	private String clave;

	@Column(name="correo_usuario")
	private String correoUsuario;

	@Column(name="estado_ususario")
	private byte estadoUsusario;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_utlima_clave")
	private Date fechaUtlimaClave;

	@Column(name="nombre_usuario")
	private String nombreUsuario;

	@Column(name="tipo_usuario")
	private String tipoUsuario;

	public Usuario() {
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getApellidoUsuario() {
		return this.apellidoUsuario;
	}

	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}

	public int getCantIntentos() {
		return this.cantIntentos;
	}

	public void setCantIntentos(int cantIntentos) {
		this.cantIntentos = cantIntentos;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCorreoUsuario() {
		return this.correoUsuario;
	}

	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}

	public byte getEstadoUsusario() {
		return this.estadoUsusario;
	}

	public void setEstadoUsusario(byte estadoUsusario) {
		this.estadoUsusario = estadoUsusario;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaUtlimaClave() {
		return this.fechaUtlimaClave;
	}

	public void setFechaUtlimaClave(Date fechaUtlimaClave) {
		this.fechaUtlimaClave = fechaUtlimaClave;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getTipoUsuario() {
		return this.tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}


}