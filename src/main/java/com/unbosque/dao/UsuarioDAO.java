package com.unbosque.dao;

import java.util.List;
import com.unbosque.entity.Usuario;

public interface UsuarioDAO {

	public void save(Usuario usuario);

	public Usuario getUsuario(String login);

	public List<Usuario> list();

	public void remove(Usuario usuario);

	public void update(Usuario usuario);

}
