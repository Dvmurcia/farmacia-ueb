package com.unbosque.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="descripcion_producto")
	private String descripcionProducto;

	@Column(name="estado_producto")
	private byte estadoProducto;

	@Column(name="existencia_producto")
	private int existenciaProducto;

	@Column(name="id_categoria")
	private int idCategoria;

	@Column(name="precio_compra")
	private int precioCompra;

	@Column(name="precio_venta")
	private int precioVenta;

	@Column(name="referencia_producto")
	private String referenciaProducto;

	@Column(name="stock_maximo")
	private int stockMaximo;

	@Column(name="stock_minimo")
	private int stockMinimo;

	@Column(name="tiene_iva")
	private byte tieneIva;

	public Producto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcionProducto() {
		return this.descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public byte getEstadoProducto() {
		return this.estadoProducto;
	}

	public void setEstadoProducto(byte estadoProducto) {
		this.estadoProducto = estadoProducto;
	}

	public int getExistenciaProducto() {
		return this.existenciaProducto;
	}

	public void setExistenciaProducto(int existenciaProducto) {
		this.existenciaProducto = existenciaProducto;
	}

	public int getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public int getPrecioCompra() {
		return this.precioCompra;
	}

	public void setPrecioCompra(int precioCompra) {
		this.precioCompra = precioCompra;
	}

	public int getPrecioVenta() {
		return this.precioVenta;
	}

	public void setPrecioVenta(int precioVenta) {
		this.precioVenta = precioVenta;
	}

	public String getReferenciaProducto() {
		return this.referenciaProducto;
	}

	public void setReferenciaProducto(String referenciaProducto) {
		this.referenciaProducto = referenciaProducto;
	}

	public int getStockMaximo() {
		return this.stockMaximo;
	}

	public void setStockMaximo(int stockMaximo) {
		this.stockMaximo = stockMaximo;
	}

	public int getStockMinimo() {
		return this.stockMinimo;
	}

	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public byte getTieneIva() {
		return this.tieneIva;
	}

	public void setTieneIva(byte tieneIva) {
		this.tieneIva = tieneIva;
	}

}