package com.chendong.api.service;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chendong.entity.UserEntity;

/**
 * 用戶服务
 * @author Administrator
 *
 */
@RequestMapping("/member")
public interface UserService {

	/**
	 * 注册服务
	 */
	@PostMapping("/regist")
	public Map<String , Object> regist(@RequestBody UserEntity userEntity);
	
	/**
	 * 登录成功后，生成对应的token 作为key 将用户userID作为value存放在redis中 返回token给客户端
	 */
	@PostMapping("/login")
	public Map<String , Object> login(@RequestBody UserEntity userEntity);
	

	/**
	 * 使用token查找用户信息
	 */
	@PostMapping("/getUser")
	public Map<String , Object> getUser(@RequestParam("token") String token);
}
