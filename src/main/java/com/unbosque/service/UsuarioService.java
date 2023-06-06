package com.unbosque.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.unbosque.dao.UsuarioDAO;
import com.unbosque.utils.HibernateUtil;
import com.unbosque.entity.Usuario;

public class UsuarioService implements UsuarioDAO {

	public void save(Usuario usuario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(usuario);
		t.commit();
	}

	public Usuario getUsuario(String login) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Usuario) session.load(Usuario.class, login);
	}

	public void update(Usuario usuario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(usuario);
		t.commit();
	}

	public void remove(Usuario usuario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(usuario);
		t.commit();
	}

	public List<Usuario> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Usuario").list();
		t.commit();
		return lista;
	}

}
