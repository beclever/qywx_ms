package com.grgbanking.css.util;

import java.util.Hashtable;
import java.util.Properties;



/**
 * date:2013.11.01
 * @author yt
 *
 */
public class CssConfigUtil {
	
	public  static final String CSS_DEFAULT_CONFIGNAME="jdbc.properties";
	
	 private static Hashtable cssProperty = new Hashtable();
     private static Properties CssDefPro=loadProperty(CSS_DEFAULT_CONFIGNAME);
     static
     {
       if(CssDefPro==null)
       {
         throw new RuntimeException("Can't Load " +CSS_DEFAULT_CONFIGNAME);
       }
     }
   
  public static String getProperty(String key)
     {
       String str = CssDefPro.getProperty(key);
       if (str != null)
       {
         return str.trim();
       }
       else
       {
         return null;
       }
     }
 
   private static Properties loadProperty(String filename)
     {
       try
       {
      
         Properties filePro = new Properties();
         filePro.load(new CssConfigUtil().getClass().getClassLoader().getResourceAsStream(filename));
         cssProperty.put(filename,filePro);
         return filePro;
       }
       catch (Exception ex)
       {
         return null;
       }
     }

}
