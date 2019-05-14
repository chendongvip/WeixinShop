package com.chendong.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chendong.base.controller.BaseController;
import com.chendong.constants.BaseApiConstarts;
import com.chendong.entity.UserEntity;
import com.chendong.feign.UserFeign;

@Controller
public class RegistController extends BaseController{

	private static final String LOCAREGIST = "locaRegist";
	private static final String LOGIN = "login";
	
	@Autowired
	private UserFeign userFeign;
	
	@RequestMapping("/locaRegist")
	public String locaRegist(){
		return LOCAREGIST;
	}
	
	@RequestMapping("/regist")
	public String regist(UserEntity userEntity,HttpServletRequest request){
		try{
			Map<String,Object> registMap = userFeign.regist(userEntity);
			Integer code = (Integer) registMap.get(BaseApiConstarts.HTTP_RES_CODE_NAME);
			if(!code.equals(BaseApiConstarts.HTTP_RES_CODE_200)){
				String msg = (String) registMap.get("msg");
				return setError(request,msg,LOCAREGIST);
			}
			//注册成功
			return LOGIN;
		}catch(Exception e){
			return setError(request,"注册失败！",LOCAREGIST);
		}
	}
}
