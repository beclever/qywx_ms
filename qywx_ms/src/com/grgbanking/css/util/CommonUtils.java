package com.grgbanking.css.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.lob.SerializableClob;
import org.springframework.beans.BeanUtils;

import com.grgbanking.common.utils.DateUtil;
import com.grgbanking.css.load.DataDictionaryLoad;

/**
 * 
 * 公用工具类 Product:Grgbanking Service Of Customer System. Version:2.0 Copyright
 * 2010 by Grgbanking All Rights Reserved. Author: zhangzhi Date: 2010-9-3
 * 上午09:39:10
 */
public class CommonUtils {

	/**
	 * 移除字符串中所有的空格
	 * 
	 * @param str
	 * @return
	 */
	public static String removeBlankSpace(String str) {
		return str == null ? null : str.trim().toUpperCase().replace(" ", "");
	}

	public static void main(String[] args) {
		System.out.println(removeBlankSpace("d     d"));
	}

	public static String toUnicode(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (char c : str.toCharArray()) {
			sb.append("\\u" + Integer.toHexString(c));
		}
		return sb.toString();
	}

	/**
	 * 把字符串【123,2345,11234,15122,1235】转为Integer集合
	 * 
	 * @return
	 */
	public static List<Integer> StringChangeToIntegerList(String str) {
		List<Integer> intList = new ArrayList<Integer>();
		str = removeBlankSpace(str);
		if (StringUtils.isNotBlank(str)) {
			for (String string : str.split(",")) {
				intList.add(Integer.valueOf(string));
			}
			return intList;
		}
		return intList;
	}

	/**
	 * 把Integer集合转为字符串【123,2345,11234,15122,1235】
	 * 
	 * @return
	 */
	public static String integerListChangeToString(List<Integer> list) {
		String sb = "";
		if (list != null && list.size() > 0) {
			for (Integer i : list) {
				sb += i + ",";
			}
			return sb.substring(0, sb.length() - 1);
		}
		return null;
	}

	/**
	 * @author:luowei
	 * @param logInfoBean
	 * @param oldObj
	 *            旧对象
	 * @param newObj
	 *            新对象
	 * @param ignoreProperties
	 *            忽略比较的字段
	 * @param dictionnaryMap
	 *            字典项对应关系,形如：{属性名称,字典类别},没有字典项时,参数为null
	 * @param args
	 *            开头显示的特殊参数,用于标识记录,例如 员工编号|姓名
	 * @createDate 2013-1-6 void
	 * @description 添加系统日志,并自动对新旧对象进行比对,找出修改内容记录日志
	 */
	public static String compare(Object oldObj, Object newObj, String ignoreProperties[],
			Map<String, String> dictionnaryMap, String... args) {
		StringBuffer result = new StringBuffer();
		try {

			Class<?> actualEditable = newObj.getClass();
			PropertyDescriptor newPds[] = BeanUtils.getPropertyDescriptors(actualEditable);
			List<?> ignoreList = ignoreProperties == null ? null : Arrays.asList(ignoreProperties);
			List<String> argsList = args == null ? null : Arrays.asList(args);
			PropertyDescriptor apropertydescriptor[];
			int j = (apropertydescriptor = newPds).length;
			for (int i = 0; i < j; i++) {
				PropertyDescriptor newPd = apropertydescriptor[i];
				if (newPd != null && newPd.getReadMethod() != null
						&& (ignoreProperties == null || !ignoreList.contains(newPd.getName()))) {
					PropertyDescriptor oldPd = BeanUtils.getPropertyDescriptor(oldObj.getClass(), newPd.getName());
					if (oldPd != null && oldPd.getReadMethod() != null) {
						try {
							Method oldReadMethod = oldPd.getReadMethod();
							if (oldPd.getName().equals("class"))
								continue;
							if (!Modifier.isPublic(oldReadMethod.getDeclaringClass().getModifiers()))
								oldReadMethod.setAccessible(true);
							Object oldvalue = oldReadMethod.invoke(oldObj, new Object[0]);
							if (oldReadMethod.getReturnType().equals(Date.class))
								oldvalue = DateUtil.formatDate((Date) oldvalue, "yyyy-MM-dd HH:mm:ss");
							if (dictionnaryMap != null && dictionnaryMap.keySet().contains(oldPd.getName()))
								oldvalue = DataDictionaryLoad.getText(dictionnaryMap.get(oldPd.getName()), ""
										+ oldvalue);
							Method newReadMethod = newPd.getReadMethod();
							if (!Modifier.isPublic(newReadMethod.getDeclaringClass().getModifiers()))
								newReadMethod.setAccessible(true);
							Object newvalue = newReadMethod.invoke(newObj, new Object[0]);
							if (argsList != null && argsList.contains(oldPd.getName()))
								result.insert(0, newvalue + "|");
							if (newReadMethod.getReturnType().equals(Date.class))
								newvalue = DateUtil.formatDate((Date) newvalue, "yyyy-MM-dd HH:mm:ss");
							if (dictionnaryMap != null && dictionnaryMap.keySet().contains(newPd.getName()))
								newvalue = DataDictionaryLoad.getText(dictionnaryMap.get(newPd.getName()), ""
										+ newvalue);
							if (null == oldvalue && null != newvalue && StringUtils.isNotEmpty(newvalue.toString())) {
								result.append("【" + oldPd.getName() + "：>>" + newvalue + "】");
							}
							if (null != oldvalue && null == newvalue && StringUtils.isNotEmpty(oldvalue.toString())) {
								result.append("【" + oldPd.getName() + "：" + oldvalue + ">>】");
							}
							if (oldvalue != null && newvalue != null && !oldvalue.equals(newvalue)) {
								result.append("【" + oldPd.getName() + "：" + oldvalue + ">>" + newvalue + "】");
							}
						} catch (Throwable ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		} catch (Exception e) {
			return e.getMessage() + "  :  " + result.toString();
		}
		if (result.lastIndexOf("|") > 0) {
			result = result.replace(result.lastIndexOf("|"), result.lastIndexOf("|") + 1, "：");
			return result.toString();
		} else
			return result.toString();
	}
	
	/**
	 * 将hibernate的SerializableClob对象转化为字符串
	 * @param serializableClob
	 * @return
	 */
	public static String converseSerializableClob2String(SerializableClob serializableClob){
		if(serializableClob == null){
			return null;
		}
        char[] buffer = null; 
		try { 
			//根据CLOB长度创建字符数组 
		    buffer = new char[(int) serializableClob.length()]; 
		    //获取CLOB的字符流Reader,并将内容读入到字符数组中 
		    serializableClob.getCharacterStream().read(buffer); 
		} catch (Exception e) {
			e.printStackTrace();
		}
        //转换为字符串 
        return String.valueOf(buffer);
	}
}
