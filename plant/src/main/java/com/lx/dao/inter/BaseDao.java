package com.lx.dao.inter;


public interface BaseDao<T> {

	public T get(String id);
	
	public T get(String columnName,String value);
}
