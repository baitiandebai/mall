package com.lx.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
/**
 * @author LX
 * HTTP 请求工具类 
 */
public class HTTPUtils {

	public static String sendGet(String url) {
		return sendGet(url, "");
	}

	public static String sendGet(String url, Map<String, String> map) {
		String params = getString(map);
		return sendGet(url, params);
	}

	public static String sendGet(String url, String params) {

		String result = "";
		BufferedReader in = null;
		String realUrl = null;
		if (params == null && !"".equals(params))
			realUrl = url + "?" + params;
		else
			realUrl = url;
		try {

			URL urlLocal = new URL(realUrl);
			URLConnection connection = urlLocal.openConnection();

			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

			connection.connect();

			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public static String sendPost(String url,Map<String,String> map){
		String params = getString(map);
		return sendPost(url, params);
	}
	
	public static String sendPost(String url,String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new PrintWriter(conn.getOutputStream());

			out.print(param);
			out.flush();

			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	private static String getString(Map<String, String> map) {

		StringBuilder sb = new StringBuilder();
		if (map != null && !map.isEmpty()) {
			sb.append("?");
			int flag = 0;
			for (String key : map.keySet()) {
				flag++;
				sb.append(key + "=" + map.get(key));
				if (flag < map.size())
					sb.append("&");
			}
		}
		return sb.toString();
	}
}
