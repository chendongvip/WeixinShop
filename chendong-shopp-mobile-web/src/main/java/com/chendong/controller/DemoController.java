package com.chendong.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.chendong.base.controller.BaseController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DemoController extends BaseController{

	private static final String INDEX="index";
	
	@RequestMapping("/indexdemo")
	public String index(HttpServletRequest request,String token){
		log.info("我的web工程搭建成功啦,userName:{}"+getUserEntity(token).getUserName());
		return INDEX;
	}
	
}
