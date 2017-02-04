package com.lx.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.Assert;

@Entity
@Table(name="lx_user_info")
public class UserInfo {

	@Id
	@Column(name="user_id")
	@GeneratedValue(generator = "uuid") 
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String userId;
	
	@Column(name="username",length=20)
	private String username;
	
	@Column(name="passwd",length=30)
	private String passwd;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="last_login_time")
	private Date lastLoginTime;
	
	@Column(name="remarks",length=100)
	private String remarks;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public void check(){
		Assert.isNull(this.username, "用户名不能为空");
		Assert.isNull(this.passwd, "密码不能为空");
	}
}
