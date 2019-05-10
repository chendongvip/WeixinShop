package com.chendong.api.service;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chendong.api.entity.UserEntity;

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
}
