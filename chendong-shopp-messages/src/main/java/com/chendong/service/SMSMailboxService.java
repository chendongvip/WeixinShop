package com.chendong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.chendong.adapter.MessageAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SMSMailboxService implements MessageAdapter{
	
	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String fromemail;
	
	@Override
	public void distribute(JSONObject jsonObject) {
		String mail = jsonObject.getString("mail");
		String userName = jsonObject.getString("userName");
		log.info("###消费者收到消息。。。mail:{},userName:{}",mail,userName);
		//发送邮件
		SimpleMailMessage message = new SimpleMailMessage();
		//谁发送
		message.setFrom(fromemail);
		//发送给谁
		message.setTo(mail);
		//邮件标题
		message.setSubject("强强科技第一次项目微信商城会员注册成功");
		//邮件内容
		message.setText("恭喜您"+ userName +"今天成为微信商城用户，谢谢您的关注");
		//打印
		log.info("######发送短信邮箱mail:{}",mail);
		//最终发送
		mailSender.send(message);
	}

}
