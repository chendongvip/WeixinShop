package com.chendong.manage.impl;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.chendong.api.entity.UserEntity;
import com.chendong.common.util.DateUtils;
import com.chendong.common.util.MD5Util;
import com.chendong.constants.DBTableName;
import com.chendong.constants.MQInterfaceType;
import com.chendong.dao.UserDao;
import com.chendong.manage.UserServiceManage;
import com.chendong.mq.producer.RegisterMailboxProducer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceManageImpl implements UserServiceManage{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RegisterMailboxProducer registerMailboxProducer;
	
	@Value("${messages.queue}")
	private String MAIL_QUEUE;
	@Override
	public void regist(UserEntity userEntity) {
		userEntity.setCreated(DateUtils.getTimestamp());
		userEntity.setUpdated(DateUtils.getTimestamp());
		String phone = userEntity.getPhone();
		String password = userEntity.getPassword();
		userEntity.setPassword(md5PassSalt(phone,password));
		userDao.save(userEntity, DBTableName.TABLE_MB_USER);
		//TODO: 注册成功后 进行邮件发送通知
		Destination activeMQQueue = new ActiveMQQueue(MAIL_QUEUE);
		String email = userEntity.getEmail();
		String userName = userEntity.getUserName();
		String mailMessage = mailMessage(email,userName);
		log.info("###regist() 注册发送邮件报文mailMessage:{}",mailMessage);
		//将信息放到mq中
		registerMailboxProducer.send(activeMQQueue, mailMessage);
	}

	@Override
	public String md5PassSalt(String phone, String password) {
		String newPass = MD5Util.MD5(phone + password);
		return newPass;
	}
	
	/**
	 * 邮件 报文
	 */
	public String mailMessage(String email,String userName){
		//组装报文格式
				JSONObject root = new JSONObject();
				JSONObject header = new JSONObject();
				JSONObject content = new JSONObject();
				header.put("interfaceType", MQInterfaceType.SMS_MAIL);
				content.put("mail",email);
				content.put("userName",userName);
				root.put("header",header);
				root.put("content", content);
				return root.toJSONString();
	}

}
