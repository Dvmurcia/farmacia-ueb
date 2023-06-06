package com.unbosque.dao;

import java.util.List;

import com.unbosque.entity.Categoria;

public interface CategoriaDAO {

	public void save(Categoria categoria);

	public Categoria getCategoria(long id);

	public List<Categoria> list();

	public void remove(Categoria categoria);

	public void update(Categoria categoria);
}
