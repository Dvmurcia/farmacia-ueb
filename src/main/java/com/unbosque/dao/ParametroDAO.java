package com.unbosque.dao;

import java.util.List;

import com.unbosque.entity.Parametro;

public interface ParametroDAO {

	public void save(Parametro parametro);

	public Parametro getParametro(long id);

	public List<Parametro> list();

	public void remove(Parametro parametro);

	public void update(Parametro parametro);
}
