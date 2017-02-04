package com.lx.service.inter;


import com.lx.dao.inter.BaseDao;

public interface BaseService<T> {

	public void setBaseDao(BaseDao<T> baseDao);
	
	public T get(String id);
	
	public T get(String columnName,String value);
}
