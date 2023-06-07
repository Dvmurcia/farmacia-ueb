package com.unbosque.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jboss.logging.Logger;

import com.unbosque.entity.*;


public class HibernateUtil {

	private static SessionFactory sessionFactory;
	private static final Logger LOGGER = Logger.getLogger(HibernateUtil.class.getName());

	private HibernateUtil() {
	}

	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {
			try {
				Configuration ac = new Configuration();
				ac.addAnnotatedClass(Usuario.class);
				ac.addAnnotatedClass(Auditoria.class);
				ac.addAnnotatedClass(Categoria.class);
				ac.addAnnotatedClass(Parametro.class);
				ac.addAnnotatedClass(Producto.class);
				sessionFactory = ac.configure().buildSessionFactory();
				LOGGER.info("Conexion a la Base de Datos realizada exitosamente");

			} catch (Throwable ex) {
				LOGGER.error("Initial SessionFactory creation failed." + ex);
				throw new ExceptionInInitializerError(ex);
			}
			return sessionFactory;
		} else {
			return sessionFactory;
		}

	}

}