package com.lx.dao;

import org.springframework.stereotype.Repository;

import com.lx.dao.inter.UserInfoDao;
import com.lx.entity.UserInfo;

@Repository
public class UserInfoDaoImpl extends BaseDaoImpl<UserInfo> implements UserInfoDao{

}
