package com.unbosque.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.unbosque.entity.Producto;
import com.unbosque.service.ProductoService;

import org.primefaces.PrimeFaces;
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
	//private byte iva;
	private Integer iva;
	private int precioVenta;
	private int precioCompra;
	//private byte estadoProd;
	private Integer estadoProd;
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
	
	public void testBtn() {
		System.out.println("Prueba ejecucion boton");
	}

	public void nuevoProducto() {
		System.out.println("creando producto...");
		Producto nuevoProducto = new Producto();

		nuevoProducto.setReferenciaProducto(refProducto);
		nuevoProducto.setDescripcionProducto(descripcionProd);
		nuevoProducto.setExistenciaProducto(existenciaProd);
		nuevoProducto.setIdCategoria(idCategoria);
		nuevoProducto.setStockMinimo(stockMin);
		nuevoProducto.setStockMaximo(stockMax);
		nuevoProducto.setTieneIva(iva.byteValue());
		nuevoProducto.setPrecioVenta(precioVenta);
		nuevoProducto.setPrecioCompra(precioCompra);
		nuevoProducto.setEstadoProducto(estadoProd.byteValue());

		this.productoService.save(nuevoProducto);
		System.out.println("Producto agregado");
		this.listaProductos = this.productoService.list();

		this.refProducto = null;
		this.descripcionProd = null;
		this.existenciaProd = 0;
		this.idCategoria = 0;
		this.stockMax = 0;
		this.stockMax = 0;
		this.iva = 0;
		this.precioCompra = 0;
		this.precioVenta = 0;
		this.estadoProd = 0;

		PrimeFaces.current().executeScript("PF('nuevoProducto').hide();");
		PrimeFaces.current().ajax().update(":form:productos");

	}

	public void reset() {
		PrimeFaces.current().resetInputs(":dialogNuevoProducto:productoGrid");
	}

	public List<Producto> getListarProductos() {
		ProductoService productoService = new ProductoService();
		return productoService.list();
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void editarMsj(RowEditEvent<Producto> event) {
		FacesMessage msj = new FacesMessage("Product Edited", String.valueOf(event.getObject().getId()));
		FacesContext.getCurrentInstance().addMessage("ID:", msj);
	}

	public void editarProducto(RowEditEvent<Producto> event) {
		System.out.println("Editar producto: " + event.getObject().getId());
		Producto productoEditado = event.getObject();
		System.out.println("producto" + productoEditado);
		productoService.update(productoEditado);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto editado"));
	}

	public void onRowCancel(RowEditEvent<Producto> event) {
		FacesMessage msj = new FacesMessage("Se cancelo la edicion del producto");
		FacesContext.getCurrentInstance().addMessage(null, msj);
	}

	public void eliminarProd() {
		System.out.println("eliminando: " +  this.prodSeleccionado);
		this.productoService.remove(this.prodSeleccionado);
		this.listaProductos.remove(this.prodSeleccionado);
		this.prodSeleccionado = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto eliminado"));
		PrimeFaces.current().ajax().update("form:msjs", "form:productos");
	}

	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}

	public void showInfo() {
		addMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Operación exitosa");
	}

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
		this.stockMin = stockMin;
	}

	public int getStockMax() {
		return stockMax;
	}

	public void setStockMax(int stockMax) {
		this.stockMax = stockMax;
	}

	public Integer getIva() {
		return iva;
	}

	public void setIva(Integer iva) {
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

	public Integer getEstadoProd() {
		return estadoProd;
	}

	public void setEstadoProd(Integer estadoProd) {
		this.estadoProd = estadoProd;
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

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
