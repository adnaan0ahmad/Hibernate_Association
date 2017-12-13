package com.java.CrudOperationsDemo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class StudentOperations implements Operations<Student> {

	public boolean add(Student t) {
		
		if (t == null) {
			throw new InvalidDataQueryException("Null values can't be added.");
		}
		else if (get(t.getStudId()) == null) {
			Session s = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = s.beginTransaction();
			s.save(t);
			transaction.commit();
			s.close();
			return true;
		} else
			throw new DuplicateEntryException("Student with same ID already exists in database.");
	}

	public Student get(int primaryKey) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = s.beginTransaction();
		Student obj = s.get(Student.class, primaryKey);
		transaction.commit();
		s.close();
		return obj;
	}

	public List<Student> getAll() {
		List<Student> l = new ArrayList<Student>();
		int key = 101;
		Student obj;
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = s.beginTransaction();
		while ((obj = s.get(Student.class, key)) != null) {
			l.add(obj);
			key++;
		}
		transaction.commit();
		s.close();
		return l;
	}

	public Student update(Student t) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = s.beginTransaction();
		if (t == null) {
			throw new InvalidDataQueryException("Null values can't be updated.");
		} else if (get(t.getStudId()) == null) {
			throw new InvalidDataQueryException("No such entry exists in Database.");
		} else {
			s.update(t);
			transaction.commit();
			s.close();
			return t;
		}
	}

	public boolean delete(int primaryKey) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = s.beginTransaction();
		if (get(primaryKey) == null) {
			throw new InvalidDataQueryException("No such entry exists in Database.");
		} else {
			s.remove(get(primaryKey));
			transaction.commit();
			s.close();
			return true;
		}
	}

	public List<Student> search(String Address) {
		if (Address == null)
			throw new InvalidDataQueryException("Please pass a valid criteria to search.");
		else {
			Student t;
			List<Student> l = getAll();
			List<Student> result = new ArrayList<Student>();
			System.out.println("Displaying list of Students with Address as: " + Address);
			Iterator<Student> itr = l.iterator();
			while (itr.hasNext()) {
				t = itr.next();
				if (t.getStudAddress().equals(Address))
					result.add(t);
			}
			if (result.isEmpty())
				throw new InvalidDataQueryException("No such Entries in Database with the specified address");
			else
				return result;
		}
	}

	public List<Student> search(int RollNumber) {
		Student t;
		List<Student> l = getAll();
		List<Student> result = new ArrayList<Student>();
		System.out.println("Displaying list of Students with RollNumber greater than: " + RollNumber);
		Iterator<Student> itr = l.iterator();
		while (itr.hasNext()) {
			t = itr.next();
			if (t.getStudRoll() >= RollNumber)
				result.add(t);
		}
		if (result.isEmpty())
			throw new InvalidDataQueryException("No such Entries in Database with the specified RollNumber");
		else
			return result;
	}

}