
package com.chendong.common.util;


import java.lang.reflect.Field;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @classDesc: 功能描述:(反射工具类F)
 * @author: 蚂蚁课堂创始人-余胜军
 * @QQ: 644064779
 * @QQ粉丝群: 116295598
 * @createTime: 2017年10月24日 下午9:45:46
 * @version: v1.0
 * @copyright:每特学院(蚂蚁课堂)上海每特教育科技有限公司
 */
@Slf4j
public class ReflectionUtils {


	/**
	 * 
	 * @methodDesc: 功能描述:(获取类的属性,拼接成字符串)
	 * @param: @return
	 */
	public static String getInsertFields(Object oj) {
		if (oj == null) {
			return null;
		}
		Class cl = oj.getClass();
		// 获取所有的属性
		Field[] declaredFields = cl.getDeclaredFields();
		String sb1 = appendFields(declaredFields);
		Class superclass = cl.getSuperclass();
		Field[] superField = superclass.getDeclaredFields();
		String sb2 = appendFields(superField);
		return sb1 + "," + sb2;
	}

	/**
	 * 
	 * @methodDesc: 功能描述:(获取类的属性,拼接成字符串的值)
	 * @param: @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 */
	public static String getInsertDeclaredFieldsValue(Object oj) {
		if (oj == null) {
			return null;
		}
		Class cl = oj.getClass();
		// 获取所有的属性
		Field[] declaredFields = cl.getDeclaredFields();
		String sb1 = appendFieldValues(declaredFields, oj);
		Class superclass = cl.getSuperclass();
		Field[] superField = superclass.getDeclaredFields();
		String sb2 = appendFieldValues(superField, oj);
		return sb1 + "," + sb2;
	}

	/**
	 * 
	 * @methodDesc: 功能描述:(获取类的属性,拼接成字符串值)
	 * @param: @param
	 *             oj
	 * @param: @return
	 */
	public static String updateAllSerField(Object oj) {
		if (oj == null) {
			return null;
		}
		Class cl = oj.getClass();
		// 获取所有的属性
		Field[] declaredFields = cl.getDeclaredFields();
		String sb1 = updateSerField(declaredFields, oj);
		Field[] supDeclaredFields = cl.getSuperclass().getDeclaredFields();
		String sb2 = updateSerField(supDeclaredFields, oj);
		return sb1 + "," + sb2;
	}

	public static String updateSerField(Field[] declaredFields, Object oj) {
		StringBuffer sb = new StringBuffer();
		try {
			for (int i = 0; i < declaredFields.length; i++) {
				String name = declaredFields[i].getName();
				if (name.equals("id")) {
					continue;
				}
				declaredFields[i].setAccessible(true);
				Object value = declaredFields[i].get(oj);
				if (value == null) {
					continue;
				}
				sb.append(name + "=" + "'" + value + "'");
				if ((i < declaredFields.length - 1)) {
					sb.append(",");
				}
			}
		} catch (Exception e) {
			log.error("###updateSerField() ERROR:", e);
		}
		return sb.toString();
	}

	 public static String appendFieldValues(Field[] declaredFields, Object oj) {
		StringBuffer sf = new StringBuffer();
		try {
			for (int i = 0; i < declaredFields.length; i++) {
				Field field = declaredFields[i];
				String name = field.getName();
				if (name == "id") {
					continue;
				}
				field.setAccessible(true);// 设置私有权限访问
				sf.append("'" + field.get(oj) + "'");
				if (i < declaredFields.length - 1) {
					sf.append(",");
				}
			}
		} catch (Exception e) {
			log.error("###ERROR:getDeclaredFieldsValue方法出现异常:", e);
		}
		return sf.toString();
	}

	 public static String appendFields(Field[] declaredFields) {
		StringBuffer sf = new StringBuffer();
		// 获取到子类的
		for (int i = 0; i < declaredFields.length; i++) {
			Field field = declaredFields[i];
			String name = field.getName();
			if (name == "id") {
				continue;
			}
			sf.append(field.getName());
			if (i < declaredFields.length - 1) {
				sf.append(",");
			}
		}
		return sf.toString();
	}

}

