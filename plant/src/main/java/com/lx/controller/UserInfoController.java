package com.lx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lx.entity.UserInfo;
import com.lx.service.inter.UserInfoService;

@Controller
@RequestMapping("/info")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping(value="index.lx",
			method=RequestMethod.GET)
	public String getHtml(){
		
		System.out.println("进入了项目了");
		
		return "index";
	}
	
	@RequestMapping(value="select.lx",produces="application/json;charset=utf-8",
			method=RequestMethod.GET)
	@ResponseBody
	public String getInfo(){
		UserInfo info = userInfoService.select("abcdeft");
		
		String str = JSONObject.toJSONString(info);
		
		return str;
	}
}
