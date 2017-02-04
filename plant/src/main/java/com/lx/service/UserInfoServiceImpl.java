package com.lx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.dao.inter.UserInfoDao;
import com.lx.entity.UserInfo;
import com.lx.service.inter.UserInfoService;

@Service
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> 
							implements UserInfoService {

//	@Autowired
//	private UserInfoDao userInfoDao;

	@Autowired
	public void setBaseDao(UserInfoDao userInfoDao){
		super.setBaseDao(userInfoDao);
	}
	

}
