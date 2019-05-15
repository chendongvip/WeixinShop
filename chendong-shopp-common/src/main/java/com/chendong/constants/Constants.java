package com.chendong.constants;

public interface Constants {
	
	//
	String USER_TOKEN = "token";

	//用户会员保存90天
	Long TERMVALIDITY = 60 * 60 * 24 * 90l;
	
	//cookie保存时间
	int COOKIETERMVALIDITY = 1000 * 60 * 60 * 24 * 90;
	
	// 购物车有效期
		Long WEBUSER_SHOP_TERMVALIDITY = 1000 * 60 * 60 * 24 * 90l;
		
		String PAY_SUCCES = "ok";
		String PAY_FAIL = "fail";
}
