package com.java.Inheritance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestInheritance {

	public static void main(String[] args) {

		Parent p1 = new Parent(1,101, 102);
		Child1 c1 = new Child1(2, 111, 112, 201, 202, 203);
		Child2 c2 = new Child2(3, 121, 122, 301, 302, 303);
	
		Configuration c = new Configuration();
		SessionFactory sf = c.configure().buildSessionFactory();
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		
		s.save(p1);
		s.save(c1);
		s.save(c2);
		
		t.commit();
		s.close();
		
	
	}

}
