package com.lx.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
/**
 * @author LX
 * 参数校验工具类
 */
public class CheckParamUtils {

	public static boolean check(String... params){
		
		for(String param : params){
			if(StringUtils.isBlank(param)){
				return true;
			}
		}
		return false;
	}
	
	public static void checkParam(Object...objects){
		for(Object param : objects){
			Assert.notNull(param,"参数不能为空");
		}
	}
}
