package com.chendong.base.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.chendong.constants.BaseApiConstarts;
import com.chendong.entity.UserEntity;
import com.chendong.feign.UserFeign;

@Controller
public class BaseController {
	public static final String ERROR = "common/error";
	@Autowired
	private UserFeign userFegin;
	
	public UserEntity getUserEntity(String token){
		Map<String , Object> userMap = userFegin.getUser(token);
		Integer code = (Integer) userMap.get(BaseApiConstarts.HTTP_RES_CODE_NAME);
		if(!code.equals(BaseApiConstarts.HTTP_RES_CODE_200)){
			return null;
		}
		//获取data里面的参数
		LinkedHashMap linkedHashMap = (LinkedHashMap) userMap.get(BaseApiConstarts.HTTP_RES_CODE_DATA);
		String json = new JSONObject().toJSONString(linkedHashMap);
		UserEntity userEntity = new JSONObject().parseObject(json,UserEntity.class);
		return userEntity;
	};
	
	public String setError(HttpServletRequest request,String msg,String address){
		request.setAttribute("error", msg);
		return address;
	}
}
