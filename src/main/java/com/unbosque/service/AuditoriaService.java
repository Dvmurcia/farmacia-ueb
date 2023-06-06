package com.unbosque.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.unbosque.dao.AuditoriaDAO;

import com.unbosque.utils.HibernateUtil;
import com.unbosque.entity.Auditoria;

public class AuditoriaService implements AuditoriaDAO {

	public void save(Auditoria auditoria) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(auditoria);
		t.commit();
	}

	public Auditoria getAuditoria(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Auditoria) session.load(Auditoria.class, id);
	}

	public List<Auditoria> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Auditoria").list();
		t.commit();
		return lista;
	}

}
