package com.chendong.manage;

import java.util.Map;

import com.chendong.entity.UserEntity;

/**
 * 功能业务逻辑
 * @author Administrator
 *
 */
public interface UserServiceManage {

	/**
	 * 注册服务逻辑
	 */
	public void regist(UserEntity userEntity);
	
	/**
	 * MD5加密功能
	 */
	public String md5PassSalt(String phone,String password);
	
	/**
	 * 用户登录功能
	 */
	public Map<String , Object> login(UserEntity userEntity);
	
	/**
	 * 获取用户
	 */
	public Map<String , Object> getUser(String token);
}
