package com.chendong.common.mybatis;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

public interface BaseDao {

	/**
	 * @InsertProvider  注释作用  自定义sql语句\
	 * 
	 * 增加持久化对象
	 */
	@InsertProvider(type=BaseProvider.class,method="save")
	public void save(@Param("obj")Object obj,@Param("table")String table);
	
	
	/**
	 * 修改持久化对象
	 */
	@InsertProvider(type=BaseProvider.class,method="update")
	public void update(@Param("obj")Object obj,@Param("table")String table,
			            @Param("idKey")Long idKey);
}
