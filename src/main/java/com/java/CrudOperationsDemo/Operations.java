package com.java.CrudOperationsDemo;

import java.util.List;

/**
 * 
 * @author Adnaan
 *
 *
 */

public interface Operations <T> {
	
	public boolean add(T t);
	
	public T get(int primaryKey);
	
	public List<T> getAll();
	
	public T update (T t);
	
	public boolean delete (int primaryKey);
	
	public List<T> searchOnCriteria(T t, SearchCriteria... criteria);

}
