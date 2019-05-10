package com.chendong.common.mybatis;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.chendong.common.util.ReflectionUtils;

/**
 * 自定义封装sql语句
 * @return
 */
public class BaseProvider {


	/**
	 * 添加
	 * @param map
	 * @return
	 */
	public String save(Map<String , Object> map){
		//实体类
		Object obj = map.get("obj");

		//表名称
		String table = (String) map.get("table");

		String sql = new SQL(){
			{
				INSERT_INTO(table);
				String columns = ReflectionUtils.getInsertFields(obj);
				String values = ReflectionUtils.getInsertDeclaredFieldsValue(obj);
				VALUES(columns,values);
			}
		}.toString();
		return sql;
	}
	
	/**
	 * 修改
	 */
	public String update(Map<String,Object> para){
		final Object obj = para.get("obj");
		final String table = (String) para.get("table");
		final Long idKey = (Long) para.get("idKey");
        String sql = new SQL(){
        	{
        		UPDATE(table);
        		SET(ReflectionUtils.updateAllSerField(obj));
        		WHERE("id="+idKey + "");
        	}
        }.toString();
        return sql;
	}
}
