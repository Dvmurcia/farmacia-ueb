package com.unbosque.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the parametro database table.
 * 
 */
@Entity
@NamedQuery(name="Parametro.findAll", query="SELECT p FROM Parametro p")
public class Parametro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="descripcion_parametro")
	private String descripcionParametro;

	@Column(name="estado_parametro")
	private byte estadoParametro;

	@Column(name="nombre_parametro")
	private String nombreParametro;

	@Column(name="valor_parametro")
	private String valorParametro;

	public Parametro() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcionParametro() {
		return this.descripcionParametro;
	}

	public void setDescripcionParametro(String descripcionParametro) {
		this.descripcionParametro = descripcionParametro;
	}

	public byte getEstadoParametro() {
		return this.estadoParametro;
	}

	public void setEstadoParametro(byte estadoParametro) {
		this.estadoParametro = estadoParametro;
	}

	public String getNombreParametro() {
		return this.nombreParametro;
	}

	public void setNombreParametro(String nombreParametro) {
		this.nombreParametro = nombreParametro;
	}

	public String getValorParametro() {
		return this.valorParametro;
	}

	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}

}