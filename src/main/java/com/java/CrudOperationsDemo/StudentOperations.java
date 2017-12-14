package com.java.CrudOperationsDemo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class StudentOperations implements Operations<Student> {

	public static void dataValidator(Student s) {
		if (s == null || s.getStudAddress().trim().isEmpty() || s.getStudName().trim().isEmpty() || s.getStudId() <= 0
				|| s.getStudRoll() <= 0)
			throw new InvalidDataQueryException("Invalid data passed as an entry for Database.");
	}

	public boolean add(Student t) {
		dataValidator(t);
		if (get(t.getStudId()) == null) {
			Session s = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = s.beginTransaction();
			try {
				s.save(t);
			} catch (Exception e) {
				transaction.rollback();
				throw new InvalidDataQueryException("Error while addition of Student Data.");
			} finally {
				HibernateUtil.resourceCleanup(s, transaction);
			}
			return true;
		} else
			throw new DuplicateEntryException("Student with same ID already exists in database.");
	}

	public Student get(int primaryKey) {
		if (primaryKey <= 0)
			throw new InvalidDataQueryException("Invalid identifier passed.");
		Student obj = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = s.beginTransaction();
		try {
			obj = s.get(Student.class, primaryKey);
		} catch (Exception e) {
			transaction.rollback();
			throw new InvalidDataQueryException("Error while retrieving of Student Data.");
		} finally {
			HibernateUtil.resourceCleanup(s, transaction);
		}
		return obj;
	}

	public List<Student> getAll() {
		List<Student> l = new ArrayList<Student>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = s.beginTransaction();
		try {
			l = s.createQuery("From Student").getResultList();
		} catch (Exception e) {
			throw new InvalidDataQueryException("Error while acquiring Student Data List.");
		} finally {
			HibernateUtil.resourceCleanup(s, transaction);
		}
		return l;
	}

	public Student update(Student t) {
		dataValidator(t);
		if (get(t.getStudId()) == null)
			throw new InvalidDataQueryException("No such entry exists in Database.");
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = s.beginTransaction();
		try {
			s.update(t);
		} catch (Exception e) {
			transaction.rollback();
			throw new InvalidDataQueryException("Error while updating Student Data.");
		} finally {
			HibernateUtil.resourceCleanup(s, transaction);
		}
		return t;
	}

	public boolean delete(int primaryKey) {
		if (get(primaryKey) == null || primaryKey <= 0)
			throw new InvalidDataQueryException("Invalid entry request in Database.");
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = s.beginTransaction();
		try {
			s.remove(get(primaryKey));
		} catch (Exception e) {
			transaction.rollback();
			throw new InvalidDataQueryException("Error in deleting the requested entry.");
		} finally {
			HibernateUtil.resourceCleanup(s, transaction);
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	public List<Student> searchOnCriteria(Student t, SearchCriteria... criteria) {
		dataValidator(t);
		if (criteria.length <= 0 || criteria.length > 3)
			throw new InvalidDataQueryException("Invalid number of search parameters passed.");

		for (int i = 0; i < criteria.length - 1; i++) {
			if (criteria[i].equals(criteria[i + 1]))
				throw new InvalidDataQueryException("Same search parameters used.");
		}

		List<Student> l = new ArrayList<Student>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = s.beginTransaction();
		Criteria cr = s.createCriteria(Student.class);

		if (criteria.length == 3) {
			if (criteria[0].equals(criteria[2]))
				throw new InvalidDataQueryException("Same search parameters used.");
			else {
				cr.add(Restrictions.eq("studName", t.getStudName()));
				cr.add(Restrictions.eq("studAddress", t.getStudAddress()));
				cr.add(Restrictions.eq("studRoll", t.getStudRoll()));
				return cr.list();
			}
		}

		else if (criteria.length == 2) {
			switch (criteria[0]) {
			case NAME:
				switch (criteria[1]) {
				case ADDRESS:
					cr.add(Restrictions.eq("studName", t.getStudName()));
					cr.add(Restrictions.eq("studAddress", t.getStudAddress()));
					l = cr.list();
					break;

				case ROLLNUMBER:
					cr.add(Restrictions.eq("studName", t.getStudName()));
					cr.add(Restrictions.eq("studRoll", t.getStudRoll()));
					l = cr.list();
					break;

				case ALL:
					throw new InvalidDataQueryException(
							"Second Search Parameter Invalid. Please enter a different Second criteria");

				default:
					throw new InvalidDataQueryException("Invalid second search criteria mentioned.");
				}
				HibernateUtil.resourceCleanup(s, transaction);
				break;

			case ADDRESS:
				switch (criteria[1]) {
				case NAME:
					cr.add(Restrictions.eq("studAddress", t.getStudAddress()));
					cr.add(Restrictions.eq("studName", t.getStudName()));
					l = cr.list();
					break;

				case ROLLNUMBER:
					cr.add(Restrictions.eq("studAddress", t.getStudAddress()));
					cr.add(Restrictions.eq("studRoll", t.getStudRoll()));
					l = cr.list();
					break;

				case ALL:
					throw new InvalidDataQueryException(
							"Second Search Parameter Invalid. Please enter a different Second criteria");

				default:
					throw new InvalidDataQueryException("Invalid second search criteria mentioned.");
				}

				HibernateUtil.resourceCleanup(s, transaction);
				break;

			case ROLLNUMBER:
				switch (criteria[1]) {
				case NAME:
					cr.add(Restrictions.eq("studName", t.getStudName()));
					cr.add(Restrictions.eq("studRoll", t.getStudRoll()));
					l = cr.list();
					break;

				case ADDRESS:
					cr.add(Restrictions.eq("studRoll", t.getStudRoll()));
					cr.add(Restrictions.eq("studAddress", t.getStudAddress()));
					l = cr.list();
					break;

				case ALL:
					throw new InvalidDataQueryException(
							"Second Search Parameter Invalid. Please enter a different Second criteria");

				default:
					throw new InvalidDataQueryException("Invalid second search criteria mentioned.");
				}
				HibernateUtil.resourceCleanup(s, transaction);
				break;

			case ALL:
				switch (criteria[1]) {
				case NAME:
				case ADDRESS:
				case ROLLNUMBER:
					throw new InvalidDataQueryException("'ALL' search criteria cannot have a second parameter");

				default:
					throw new InvalidDataQueryException("Invalid first search criteria mentioned.");
				}
			}
		}

		else {
			switch (criteria[0]) {
			case NAME:
				l = cr.add(Restrictions.eq("studName", t.getStudName())).list();
				HibernateUtil.resourceCleanup(s, transaction);
				break;

			case ADDRESS:
				l = cr.add(Restrictions.eq("studAddress", t.getStudAddress())).list();
				HibernateUtil.resourceCleanup(s, transaction);
				break;

			case ROLLNUMBER:
				l = cr.add(Restrictions.eq("studRoll", t.getStudRoll())).list();
				HibernateUtil.resourceCleanup(s, transaction);
				break;

			case ALL:
				l = getAll();
				break;

			default:
				throw new InvalidDataQueryException("Invalid first search criteria mentioned.");

			}

		}

		return l;
	}

}