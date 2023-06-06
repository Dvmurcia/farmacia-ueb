package com.unbosque.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import com.unbosque.entity.Producto;
import com.unbosque.service.ProductoService;

@ManagedBean
@SessionScoped
public class ProductoMB {
	/*
	 * private int id; private String refProducto; private int idProducto; private
	 * String descripcionProd; private int existenciaProd; private int StockMin;
	 * private int StockMax; private int iva; private int precioVenta; private int
	 * precioCompra; private int estadoProd;
	 */
	private Producto producto;
	private DataModel listaProductos;

	public List<Producto> getListarProductos() {
		ProductoService productoService = new ProductoService();
		return productoService.list();
	}

	public DataModel getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(DataModel listaProductos) {
		this.listaProductos = listaProductos;
	}

}
