package com.unbosque.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.inject.Inject;

import org.jboss.logging.Logger;
import com.unbosque.entity.Parametro;
import com.unbosque.service.ParametroService;

@ManagedBean
@SessionScoped
public class ListarParametroMB {
	private final static Logger LOGGER = Logger.getLogger(ListarParametroMB.class.getName());
	private List<Parametro> parametros;
	private Parametro parametroSeleccionado;

	@Inject
	private ParametroService parametroService;

	@PostConstruct
	public void init() {
		this.parametroService = new ParametroService();
		this.parametros = this.parametroService.list();
		LOGGER.info("Parametros cargados de la BD: " + this.parametros.size());
	}

	public Parametro getParametroSeleccionado() {
		return parametroSeleccionado;
	}

	public void setParametroSeleccionado(Parametro parametroSeleccionado) {
		this.parametroSeleccionado = parametroSeleccionado;
	}

	public List<Parametro> getParametros() {
		return parametros;
	}

	public void setParametroService(ParametroService parametroService) {
		this.parametroService = parametroService;
	}

}
