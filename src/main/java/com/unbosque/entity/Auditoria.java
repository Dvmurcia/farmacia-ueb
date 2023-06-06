package com.unbosque.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the auditoria database table.
 * 
 */
@Entity
@NamedQuery(name="Auditoria.findAll", query="SELECT a FROM Auditoria a")
public class Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="accion_auditoria")
	private String accionAuditoria;

	@Column(name="direccion_ip")
	private String direccionIp;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_auditoria")
	private Date fechaAuditoria;

	@Column(name="id_tabla")
	private int idTabla;

	@Column(name="id_usuario")
	private String idUsuario;

	@Column(name="nombre_tabla")
	private String nombreTabla;

	public Auditoria() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccionAuditoria() {
		return this.accionAuditoria;
	}

	public void setAccionAuditoria(String accionAuditoria) {
		this.accionAuditoria = accionAuditoria;
	}

	public String getDireccionIp() {
		return this.direccionIp;
	}

	public void setDireccionIp(String direccionIp) {
		this.direccionIp = direccionIp;
	}

	public Date getFechaAuditoria() {
		return this.fechaAuditoria;
	}

	public void setFechaAuditoria(Date fechaAuditoria) {
		this.fechaAuditoria = fechaAuditoria;
	}

	public int getIdTabla() {
		return this.idTabla;
	}

	public void setIdTabla(int idTabla) {
		this.idTabla = idTabla;
	}

	public String getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreTabla() {
		return this.nombreTabla;
	}

	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}

}