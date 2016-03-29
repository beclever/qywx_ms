package com.grgbanking.common.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.grgbanking.common.consts.WsConsts;
import com.grgbanking.core.entity.common.AttachmentBean;

/**
 * 下载文件并保存到本地
 * @author wsh
 *
 */
public class WorkFormFileUtil {

    Logger logger = Logger.getLogger(getClass());
    
	//文件子目录
	private String subPath = null;
	
	private String path = null;

	public WorkFormFileUtil(){
        //String rootPath = WsConsts.AttachmentRootPath;
	    //String rootPath = "D:\\qywxFile";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date date = new Date();
		subPath = sdf.format(date);
		path = WsConsts.AttachmentRootPath + File.separator + subPath;
	}
    /**
	 * 
	 * @param urlStr
	 */
	public JSONArray downLoadFromUrl(List<String> urlList, String longitude, String latitude){
		

		//用于保存到数据库的附件信息
		JSONArray jsonArr = null;
		jsonArr = new JSONArray();
		try {
			
			File fileDir = new File(path);
			//判断路径是否存在
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			
			//定义文件输出流
			FileOutputStream fos = null;
			
			//定义输入流，用于接受 URL连接获取的输入流
			InputStream input = null;
			
			URL url = null;
			URLConnection urlConn = null;
			
			//文件下载的地址
			String urlStr = "";
			
			
			//遍历传进来的URL列表，批量下载文件
			for (int i = 0; i < urlList.size(); i++) {

				urlStr = urlList.get(i);
				System.out.println("urlStr:"+urlStr);
		    	url = new URL(urlStr);  
				urlConn = url.openConnection();
				
				System.out.println("Content-Type: " + urlConn.getContentType());  		
				

				//要下载的文件后缀
				String contentType = urlConn.getContentType();
				String imgType = ".jpg";
				if("image/jpeg".contains(contentType)){
					imgType = ".jpg";
				}else if("image/png".contains(contentType)){
					imgType = ".png";
				}else if("image/gif".contains(contentType)){
					imgType = ".gif";
				}else if("image/bmp".contains(contentType)){
					imgType = ".bmp";
				}
				
				String imgId = "qywx_ms" + DateUtil.getDate("yyyyMMddHHmmss")+NumberUtils.getRandom(4);//UUID.randomUUID().toString();
				String realPath = path + File.separator  + imgId+imgType;
				
				//用于保存到数据库的附件信息
				AttachmentBean attachmentBean = new AttachmentBean(imgId,subPath + File.separator  + imgId+imgType);
				attachmentBean.setLatitude(latitude);
				attachmentBean.setLongitude(longitude);
				jsonArr.add((JSONObject) JSON.toJSON(attachmentBean));
				
				fos = new FileOutputStream(realPath);  

				int length = urlConn.getContentLength(); 
				if (length > 0) { 
				    input = urlConn.getInputStream();  
				    byte[] buffer = new byte[1024];
			        int len = 0;
			        while ((len = input.read(buffer)) > 0) //循环读取文件到输出流
			        {
			           fos.write(buffer , 0 , len);   //使用输出流输出文件。
			        }
				} else {  
				    System.out.println("The "+(i+1)+"File No Content.");  
				}
			}
			if(fos != null){
				fos.flush();  
				fos.close();
			}
	        if(input != null){
	        	input.close();
	        }
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		System.out.println("此次上传的附件总数："+jsonArr.size());
		return jsonArr;
	}
	
	public String downLoadFrom(File file) {
        String fileName = "";
        try {
            File fileDir = new File(path);
            // 判断路径是否存在
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            // 定义文件输出流
            FileOutputStream fos = null;
            // 定义输入流，用于接受 URL连接获取的输入流
            InputStream input = null;
            // 遍历传进来的URL列表，批量下载文件
            if (null != file) {
                // 要下载的文件后缀
                String contentType = new MimetypesFileTypeMap()
                        .getContentType(file);
                String imgType = ".jpg";
                if ("image/jpeg".contains(contentType)) {
                    imgType = ".jpg";
                } else if ("image/png".contains(contentType)) {
                    imgType = ".png";
                } else if ("image/gif".contains(contentType)) {
                    imgType = ".gif";
                } else if ("image/bmp".contains(contentType)) {
                    imgType = ".bmp";
                }
                fileName = "qywx_ms" + DateUtil.getDate("yyyyMMddHHmmss")+NumberUtils.getRandom(4)+imgType;
                // 用于保存到数据库的附件信息
                fos = new FileOutputStream(path + File.separator  + fileName);
                long length = file.length();
                if (length > 0) {
                    input = new FileInputStream(file);
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = input.read(buffer)) > 0) // 循环读取文件到输出流
                    {
                        fos.write(buffer, 0, len); // 使用输出流输出文件。
                    }
                }
            }
            if (fos != null) {
                fos.flush();
                fos.close();
            }
            if (input != null) {
                input.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("图片保存本地服务器失败！");
        }
        return subPath + File.separator  + fileName;
    }
	
}
