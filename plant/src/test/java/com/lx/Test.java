package com.lx;

import com.lx.util.HTTPUtils;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="../../../../applicationContext.xml")
public class Test {

	public static void main(String[] args) {
		String str = HTTPUtils.sendGet("http://127.0.0.1:8180/plant/info/select.html");
		System.out.println(str);
	}
}
