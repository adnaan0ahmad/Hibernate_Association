package com.java.CrudOperationsDemo;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * 
 * @author Adnaan
 *
 *
 */
public class HibernateUtil {
	
	private static SessionFactory sessionFactory = null;
	
	public static SessionFactory getSessionFactory() {
		try {
		if (sessionFactory == null) 
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}catch(Exception e) {
			throw new InvalidDataQueryException("Please check the Seession Factory.");
		}
			return sessionFactory;
		
	}
	
	public static void resourceCleanup(Session s, Transaction t) {
		if (t!=null)
			t.commit();
		if (s!=null)
			s.close();
	}
	
	
	
}
