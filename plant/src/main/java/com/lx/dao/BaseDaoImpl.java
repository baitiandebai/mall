package com.lx.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.lx.dao.inter.BaseDao;
import com.lx.util.CheckParamUtils;

@Repository
public class BaseDaoImpl<T> implements BaseDao<T>{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Class<T> clazz;
	
//	从类的泛型中获得集成该类的子类的POJO对象放入到clazz中
	@SuppressWarnings("unchecked")
	public BaseDaoImpl(){
		this.clazz = null;
		@SuppressWarnings("rawtypes")
		Class c = getClass();
		Type type = c.getGenericSuperclass();
		if(type instanceof ParameterizedType){
			Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
			this.clazz = (Class<T>) parameterizedType[0];
		}
	}
	
	protected Session getSession() {
		Session session = sessionFactory.openSession();
		if(session == null)
			session = sessionFactory.getCurrentSession();
		return session;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T get(String id){
		Assert.notNull(id,"参数不能为空");
		return (T) getSession().get(clazz, id);
	}
	
	@SuppressWarnings("unchecked")
	public T get(String columnName,String value){
	
		CheckParamUtils.checkParam(columnName,value);
	
		StringBuffer sb = new StringBuffer();
		sb.append("from " + clazz.getName() + " as model ");
		sb.append("where model."+columnName + "= :value");
		return (T) getSession().createQuery(sb.toString()).
				setParameter("value", value).uniqueResult();
	}
}
