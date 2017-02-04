package com.lx.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lx.entity.UserInfo;
import com.lx.service.inter.UserInfoService;
import com.lx.util.CheckParamUtils;
import com.lx.util.Constants;
import com.lx.util.VerifyCodeUtils;

@Controller
@RequestMapping("/info")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;
	
	
	@RequestMapping(value="code.lx",produces="image/jpeg")
	public void getVerifyCode(HttpServletResponse response,HttpServletRequest request){
		
		String code = VerifyCodeUtils.generateVerifyCode(4);
		HttpSession session = request.getSession();
		session.setAttribute("code", code);
		try {
			VerifyCodeUtils.outputImage(200, 80, response.getOutputStream(), code);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	consumes="application/x-www-form-urlencoded;chartset=utf-8" post方式单个接受参数
	@RequestMapping(value="login.lx",method=RequestMethod.POST,
			produces="application/json;charset=utf-8")
	@ResponseBody
	public String login(@RequestBody String json,HttpServletRequest request){
		
		JSONObject object = JSONObject.parseObject(json);
		
		String username = object.getString("username");
		String passwd = object.getString("passwd");
		String code = object.getString("code");
		
		if(CheckParamUtils.check(username,passwd)){
			return Constants.EMPTY_PARAM;
		}
		
		HttpSession session = request.getSession();
		String codeLocal = (String) session.getAttribute("code");
		if(!StringUtils.equalsIgnoreCase(code, codeLocal)){
			return Constants.CODE_ERROR;
		}
		
		UserInfo user = userInfoService.get("username", username);
		if(user == null)
			return Constants.FAIL;
		passwd = DigestUtils.md5Hex(passwd);
		if(!StringUtils.equalsIgnoreCase(passwd, user.getPasswd())){
			return Constants.FAIL;
		}
		return Constants.SUCCESS;
	}
}
