package com.grgbanking.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

/**
 * 项目名称：ms   
 * 类名称：ConversionJSONData   
 * 类描述： 接受客户端的信息
 * 创建人：yjie   
 * 创建时间：2012-5-17 上午09:52:05   
 * 修改人：Administrator   
 * 修改时间：2012-5-17 上午09:52:05   
 * 修改备注：   
 * @version    
 *
 */
public class ConversionJSONData {

	public static JSONObject getJsonData(HttpServletRequest request) {
		String date = getReadToString(request);
		JSONObject jsonObject = JSONObject.fromObject(date);
		return jsonObject;
	}
	
	/**
	 * 读取客户端传输过来的json字符串
	 * 
	 * @param input
	 * @return
	 */
	private static String getReadToString(HttpServletRequest request) {
		String requestXml = "";
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			InputStream input = request.getInputStream();
			int c = input.read();
			while (c != -1) {
				baos.write(c);
				c = input.read();
			}
			byte[] data = baos.toByteArray();
			String requestxml = new String(data, "utf-8");
			try {
				if (!"".equals(requestxml)) {
					requestXml = new String(Base64.decode(requestxml.getBytes()), "utf-8");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					baos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestXml;

	}

	
}
