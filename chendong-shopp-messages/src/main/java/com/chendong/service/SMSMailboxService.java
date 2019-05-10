package com.chendong.service;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.chendong.adapter.MessageAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SMSMailboxService implements MessageAdapter{

	@Override
	public void distribute(JSONObject jsonObject) {
		String mail = jsonObject.getString("mail");
		String userName = jsonObject.getString("userName");
		log.info("###消费者收到消息。。。mail:{},userName:{}",mail,userName);
		//发送邮件
		
	}

}
