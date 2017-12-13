package com.java.CrudOperationsDemo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory = null;
	
	private HibernateUtil() {
	}
	
	public static SessionFactory getSessionFactory() {
		
		if (sessionFactory == null) {
			Configuration cf = new Configuration();
			sessionFactory = cf.configure().buildSessionFactory();
		}
		
			return sessionFactory;
		
	}
	
}
