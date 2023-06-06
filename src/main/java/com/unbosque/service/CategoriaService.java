package com.unbosque.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.unbosque.dao.CategoriaDAO;

import com.unbosque.utils.HibernateUtil;
import com.unbosque.entity.Categoria;

public class CategoriaService implements CategoriaDAO{


	public void save(Categoria categoria) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(categoria);
		t.commit();
	}

	public Categoria getCategoria(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Categoria) session.load(Categoria.class, id);
	}

	public void update(Categoria categoria) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(categoria);
		t.commit();
	}

	public void remove(Categoria categoria) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(categoria);
		t.commit();
	}

	public List<Categoria> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Categoria").list();
		t.commit();
		return lista;
	}


}
