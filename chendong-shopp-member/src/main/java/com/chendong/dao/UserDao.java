package com.chendong.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.chendong.common.mybatis.BaseDao;
import com.chendong.entity.UserEntity;

@Mapper
public interface UserDao extends BaseDao{

	@Select("Select ID,USERNAME,PASSWORD,EMAIL,created,updated from mb_user"
			+ " where PHONE=#{phone} and PASSWORD=#{password}")
	public UserEntity getUserPhoneAndPwd(@Param("phone")String phone
			,@Param("password")String password);
	
	@Select("Select ID,USERNAME,PASSWORD,EMAIL,created,updated from mb_user"
			+ " where id=#{id}")
	public UserEntity getUserInfo(@Param("id")Long id);
}
