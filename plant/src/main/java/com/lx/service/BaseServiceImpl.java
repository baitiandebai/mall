package com.lx.service;


import com.lx.dao.inter.BaseDao;
import com.lx.service.inter.BaseService;

public class BaseServiceImpl<T> implements BaseService<T> {
	
	
	public BaseDao<T> baseDao;

	@Override
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public T get(String id){
		return baseDao.get(id);
	}

	@Override
	public T get(String columnName, String value) {
		return baseDao.get(columnName, value);
	}
}
