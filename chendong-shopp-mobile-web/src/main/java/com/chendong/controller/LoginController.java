package com.chendong.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chendong.base.controller.BaseController;
import com.chendong.common.util.CookieUtil;
import com.chendong.constants.BaseApiConstarts;
import com.chendong.constants.Constants;
import com.chendong.entity.UserEntity;
import com.chendong.feign.UserFeign;

@Controller
public class LoginController extends BaseController{

	private static final String LGOIN = "login";
	private static final String INDEX = "index";
	
	@Autowired
	private UserFeign userFeign;
	
	@RequestMapping("/locaLogin")
	public String locaLogin(){
		return LGOIN;
	}
	
	@RequestMapping("/login")
	public String login(UserEntity userEntity,HttpServletRequest request,HttpServletResponse response){
		Map<String , Object> loginMap = userFeign.login(userEntity);
		Integer code = (Integer) loginMap.get(BaseApiConstarts.HTTP_RES_CODE_NAME);
		if(!code.equals(BaseApiConstarts.HTTP_RES_CODE_200)){
			String msg = (String) loginMap.get("msg");
			return setError(request,msg,LGOIN);
		}
		//登录成功后，获取token，将这个token存放在cookie中
		String token = (String) loginMap.get("data");
		CookieUtil.addCookie(response,Constants.USER_TOKEN, token, Constants.COOKIETERMVALIDITY);
		return INDEX;
		
	}
}
