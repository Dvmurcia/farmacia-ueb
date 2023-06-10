package com.unbosque.bean;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;

import com.unbosque.entity.Parametro;
import com.unbosque.entity.Auditoria;
import com.unbosque.service.AuditoriaService;
import com.unbosque.service.ParametroService;
import com.unbosque.utils.IdentificadorIP;

@ManagedBean
@RequestScoped
public class ListarParametroMB {
	private final static Logger LOGGER = Logger.getLogger(ListarParametroMB.class.getName());
	private List<Parametro> parametros;
	private Parametro parametro;
	private Parametro parametroSelec;

	// Campos del formulario de creacion
	private String nombreParametro;
	private String descripcionParametro;
	private String valorParametro;
	private Integer estadoParametro;

	@Inject
	private ParametroService parametroService;

	@Inject
	private AuditoriaService auditoriaService;

	@ManagedProperty(value = "#{loginMB}")
	private LoginMB sesion;

	@PostConstruct
	public void init() {
		this.parametroService = new ParametroService();
		this.auditoriaService = new AuditoriaService();
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
		generarAuditoria("I", nuevoParametro);
	}

	public void editarPara(RowEditEvent<Parametro> event) {
		System.out.println("editando...");
		System.out.println("Editar parametro: " + event.getObject().getId());
		Parametro parametroEditado = event.getObject();
		System.out.println(parametroEditado + ": Parametro");
		parametroService.update(parametroEditado);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Parametro editado"));

		generarAuditoria("U", event.getObject());
	}

	public void onRowCancel(RowEditEvent<Parametro> event) {
		FacesMessage msj = new FacesMessage("Se cancelo la edicion del parametro");
		FacesContext.getCurrentInstance().addMessage(null, msj);
	}

	public void eliminarPara() {
		System.out.println("eliminando: " + parametroSelec);
		this.parametroService.remove(this.parametroSelec);
		this.parametros.remove(this.parametroSelec);
		generarAuditoria("D", this.parametroSelec);
		this.parametroSelec = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Parametro eliminado"));
		PrimeFaces.current().ajax().update("form:msjs", "form:dt-parametros");
	}

	private void generarAuditoria(String accion, Parametro elemento) {
		try {
			Auditoria audit = new Auditoria();
			IdentificadorIP ip = new IdentificadorIP();
			audit.setIdUsuario(sesion.getUsuario());
			audit.setFechaAuditoria(new Date());
			audit.setAccionAuditoria(accion);
			audit.setIdTabla((elemento.getId() == 0) ? 0 : elemento.getId());
			audit.setNombreTabla("producto");
			audit.setDireccionIp(ip.getIP());

			this.auditoriaService.save(audit);
			System.out.println("Auditoria registrada exitosamente");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}

	public void showInfo() {
		addMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Operaci�n exitosa");
	}

	public void showWarn() {
		addMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Message Content");
	}

	public void showError() {
		addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al realizar la operaci�n");
	}

	public List<Parametro> getParametros() {
		return parametros;
	}

	public void setParametroService(ParametroService parametroService) {
		this.parametroService = parametroService;
	}

	public void setAuditoriaService(AuditoriaService auditoriaService) {
		this.auditoriaService = auditoriaService;
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

	public Parametro getParametro() {
		return parametro;
	}

	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	

	public Parametro getParametroSelec() {
		return parametroSelec;
	}

	public void setParametroSelec(Parametro parametroSelec) {
		this.parametroSelec = parametroSelec;
	}

	public void setParametros(List<Parametro> parametros) {
		this.parametros = parametros;
	}

	public LoginMB getSesion() {
		return sesion;
	}

	public void setSesion(LoginMB sesion) {
		this.sesion = sesion;
	}
}
