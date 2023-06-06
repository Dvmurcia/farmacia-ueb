package com.unbosque.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.unbosque.entity.*;


public class HibernateUtil {

	private static SessionFactory sessionFactory;

	private HibernateUtil() {
	}

	@SuppressWarnings("deprecation")
	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {
			try {
				@SuppressWarnings("deprecation")
				Configuration ac = new Configuration();
				ac.addAnnotatedClass(Usuario.class);
				ac.addAnnotatedClass(Auditoria.class);
				ac.addAnnotatedClass(Categoria.class);
				ac.addAnnotatedClass(Parametro.class);
				ac.addAnnotatedClass(Producto.class);
				sessionFactory = ac.configure().buildSessionFactory();

			} catch (Throwable ex) {
				// Log the exception.
				System.err.println("Initial SessionFactory creation failed." + ex);
				throw new ExceptionInInitializerError(ex);
			}
			return sessionFactory;
		} else {
			return sessionFactory;
		}

	}

}