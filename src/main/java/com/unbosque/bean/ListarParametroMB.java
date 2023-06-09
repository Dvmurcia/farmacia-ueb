package com.unbosque.bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.primefaces.PrimeFaces;

import com.unbosque.entity.Parametro;
import com.unbosque.service.ParametroService;

@ManagedBean
@SessionScoped
public class ListarParametroMB {
	private final static Logger LOGGER = Logger.getLogger(ListarParametroMB.class.getName());
	private List<Parametro> parametros;

	// Campos del formulario de creacion
	private String nombreParametro;
	private String descripcionParametro;
	private String valorParametro;
	private Integer estadoParametro;

	@Inject
	private ParametroService parametroService;

	@PostConstruct
	public void init() {
		this.parametroService = new ParametroService();
		this.parametros = this.parametroService.list();
	}

	public void reset() {
		PrimeFaces.current().resetInputs(":dialogNuevoParametro:parametroGrid");
	}

	public void crearParametro() {
		Parametro nuevoParametro = new Parametro();

		nuevoParametro.setNombreParametro(nombreParametro);
		nuevoParametro.setDescripcionParametro(descripcionParametro);
		nuevoParametro.setValorParametro(valorParametro);
		nuevoParametro.setEstadoParametro(estadoParametro.byteValue());

		this.parametroService.save(nuevoParametro);
		System.out.println("Parametro insertado correctamente");
		this.parametros = this.parametroService.list();

		this.nombreParametro = null;
		this.descripcionParametro = null;
		this.valorParametro = null;
		this.estadoParametro = null;
		
		PrimeFaces.current().executeScript("PF('nuevoParametro').hide();");
		PrimeFaces.current().ajax().update(":form:dt-parametros");
		// TODO Pendiente hacer registro de auditoria
	}

	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}

	public void showInfo() {
		addMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Operación exitosa");
	}

	public void showWarn() {
		addMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Message Content");
	}

	public void showError() {
		addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al realizar la operación");
	}

	public List<Parametro> getParametros() {
		return parametros;
	}

	public void setParametroService(ParametroService parametroService) {
		this.parametroService = parametroService;
	}

	public String getNombreParametro() {
		return nombreParametro;
	}

	public void setNombreParametro(String nombreParametro) {
		this.nombreParametro = nombreParametro;
	}

	public String getDescripcionParametro() {
		return descripcionParametro;
	}

	public void setDescripcionParametro(String descripcionParametro) {
		this.descripcionParametro = descripcionParametro;
	}

	public String getValorParametro() {
		return valorParametro;
	}

	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}

	public Integer getEstadoParametro() {
		return estadoParametro;
	}

	public void setEstadoParametro(Integer estadoParametro) {
		this.estadoParametro = estadoParametro;
	}
}
