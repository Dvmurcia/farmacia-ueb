package com.unbosque.dao;

import java.util.List;

import com.unbosque.entity.Producto;

public interface ProductoDAO {

	public void save(Producto producto);

	public Producto getProducto(long id);

	public List<Producto> list();

	public void remove(Producto producto);

	public void update(Producto producto);

}
