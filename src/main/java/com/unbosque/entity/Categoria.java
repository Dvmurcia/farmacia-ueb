package com.unbosque.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
@Table(name="categoria")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="estado_categoria")
	private byte estadoCategoria;

	@Column(name="nombre_categoria")
	private String nombreCategoria;

	public Categoria() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getEstadoCategoria() {
		return this.estadoCategoria;
	}

	public void setEstadoCategoria(byte estadoCategoria) {
		this.estadoCategoria = estadoCategoria;
	}

	public String getNombreCategoria() {
		return this.nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

}