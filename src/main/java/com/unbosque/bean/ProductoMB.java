package com.unbosque.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

import com.unbosque.entity.Producto;
import com.unbosque.service.ProductoService;

import org.primefaces.PrimeFaces;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

@ManagedBean
@SessionScoped
public class ProductoMB {

	private int id;
	private String refProducto;
	private String descripcionProd;
	private int existenciaProd;
	private int idCategoria;
	private int stockMin;
	private int stockMax;
	private int iva;
	private int precioVenta;
	private int precioCompra;
	private int estadoProd;
	private Producto producto;
	private List<Producto> listaProductos;
	private Producto prodSeleccionado;

	@Inject
	private ProductoService productoService;

	@PostConstruct
	private void init() {
		this.productoService = new ProductoService();
		this.listaProductos = getListarProductos();

	}

	public List<Producto> getListarProductos() {
		ProductoService productoService = new ProductoService();
		return productoService.list();
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void editarMsj(RowEditEvent<Producto> event) {
		FacesMessage msj = new FacesMessage("Product Edited", String.valueOf(event.getObject().getClass()));
		FacesContext.getCurrentInstance().addMessage(null, msj);
	}

	public void onRowCancel(RowEditEvent<Producto> event) {
		FacesMessage msj = new FacesMessage("Se cancelo la edicion del producto");
		FacesContext.getCurrentInstance().addMessage(null, msj);
	}

	public void eliminarProd() {
		this.productoService.remove(this.prodSeleccionado);
		this.listaProductos.remove(this.prodSeleccionado);
		this.prodSeleccionado = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto eliminado"));
		PrimeFaces.current().ajax().update("form:msjs", "form:productos");
	}

	// public void setListaProductos(DataModel listaProductos) {
	// this.listaProductos = listaProductos;
	// }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRefProducto() {
		return refProducto;
	}

	public void setRefProducto(String refProducto) {
		this.refProducto = refProducto;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDescripcionProd() {
		return descripcionProd;
	}

	public void setDescripcionProd(String descripcionProd) {
		this.descripcionProd = descripcionProd;
	}

	public int getExistenciaProd() {
		return existenciaProd;
	}

	public void setExistenciaProd(int existenciaProd) {
		this.existenciaProd = existenciaProd;
	}

	public int getStockMin() {
		return stockMin;
	}

	public void setStockMin(int stockMin) {
		stockMin = stockMin;
	}

	public int getStockMax() {
		return stockMax;
	}

	public void setStockMax(int stockMax) {
		stockMax = stockMax;
	}

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public int getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(int precioVenta) {
		this.precioVenta = precioVenta;
	}

	public int getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(int precioCompra) {
		this.precioCompra = precioCompra;
	}

	public int getEstadoProd() {
		return estadoProd;
	}

	public Producto getProdSeleccionado() {
		return prodSeleccionado;
	}

	public void setProdSeleccionado(Producto prodSeleccionado) {
		this.prodSeleccionado = prodSeleccionado;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public void setEstadoProd(int estadoProd) {
		this.estadoProd = estadoProd;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
