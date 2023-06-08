package com.unbosque.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.unbosque.dao.ParametroDAO;
import com.unbosque.utils.HibernateUtil;
import com.unbosque.entity.Parametro;

public class ParametroService implements ParametroDAO {

	public void save(Parametro parametro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(parametro);
		t.commit();
	}

	public Parametro getParametro(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Parametro) session.load(Parametro.class, id);
	}

	public void update(Parametro parametro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(parametro);
		t.commit();
	}

	public void remove(Parametro parametro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(parametro);
		t.commit();
	}

	public List<Parametro> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Parametro").list();
		t.commit();
		return lista;
	}

}
