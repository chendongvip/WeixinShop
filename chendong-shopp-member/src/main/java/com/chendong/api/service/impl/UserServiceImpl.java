package com.chendong.api.service.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chendong.api.entity.UserEntity;
import com.chendong.api.service.UserService;
import com.chendong.common.api.BaseApiService;
import com.chendong.manage.UserServiceManage;

import lombok.extern.slf4j.Slf4j;

/**
 * 会员服务实现类
 * @author Administrator
 *
 */
@Slf4j
@RestController
public class UserServiceImpl extends BaseApiService implements UserService{

	@Autowired
	private UserServiceManage userServiceManage;
	
	@Override
	public Map<String, Object> regist(@RequestBody UserEntity userEntity) {
		if(StringUtils.isEmpty(userEntity.getUserName())){
			return setResultParamterError("用户名称不能为空");
		}
		if(StringUtils.isEmpty(userEntity.getPassword())){
			return setResultParamterError("密码不能为空");
		}
		try{
			userServiceManage.regist(userEntity);
			return setResultSuccess();
		}catch(Exception e){
			log.error("###regist() ERROR:" ,e);
			return setResultError("注册失败");
		}
		//return null;
	}

}
