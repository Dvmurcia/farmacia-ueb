package com.unbosque.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.unbosque.dao.ProductoDAO;
import com.unbosque.utils.HibernateUtil;
import com.unbosque.entity.Producto;

public class ProductoService implements ProductoDAO {


	public void save(Producto producto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(producto);
		t.commit();
	}

	public Producto getProducto(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Producto) session.load(Producto.class, id);
	}

	public void update(Producto producto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(producto);
		t.commit();
	}

	public void remove(Producto producto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(producto);
		t.commit();
	}

	public List<Producto> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Producto").list();
		t.commit();
		return lista;
	}


}
