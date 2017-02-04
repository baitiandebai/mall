package com.lx.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lx.dao.inter.UserInfoDao;
import com.lx.entity.UserInfo;

@Repository
public class UserInfoDaoImpl implements UserInfoDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public UserInfo select(String id){
		
		Session session = sessionFactory.openSession();
		
		UserInfo userInfo = (UserInfo) session.get(UserInfo.class,id);
		
		return userInfo;
	}
	
}
