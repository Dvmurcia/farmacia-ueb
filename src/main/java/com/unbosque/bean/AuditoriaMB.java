package com.unbosque.bean;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import com.unbosque.entity.Auditoria;
import com.unbosque.service.AuditoriaService;

@ManagedBean
@SessionScoped
public class AuditoriaMB {
	private int id;
	private String idUsuario;
	private Date fechaAuditoria;
	private int idTabla;
	private String nombreTabla;
	private String accionAuditoria;

	private Auditoria auditoria;
	private List<Auditoria> listaAuditorias;

	@Inject
	private AuditoriaService auditoriaService;

	@PostConstruct
	private void init() {
		this.auditoriaService = new AuditoriaService();
		this.listaAuditorias = getListarAuditorias();
	}

	public List<Auditoria> getListarAuditorias() {
		return this.auditoriaService.list();
	}

	public Auditoria getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(Auditoria auditoria) {
		this.auditoria = auditoria;
	}

	public List<Auditoria> getListaAuditorias() {
		return listaAuditorias;
	}

	public void setListaAuditorias(List<Auditoria> listaAuditorias) {
		this.listaAuditorias = listaAuditorias;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Date getFechaAuditoria() {
		return fechaAuditoria;
	}

	public void setFechaAuditoria(Date fechaAuditoria) {
		this.fechaAuditoria = fechaAuditoria;
	}

	public int getIdTabla() {
		return idTabla;
	}

	public void setIdTabla(int idTabla) {
		this.idTabla = idTabla;
	}

	public String getNombreTabla() {
		return nombreTabla;
	}

	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}

	public String getAccionAuditoria() {
		return accionAuditoria;
	}

	public void setAccionAuditoria(String accionAuditoria) {
		this.accionAuditoria = accionAuditoria;
	}

}
