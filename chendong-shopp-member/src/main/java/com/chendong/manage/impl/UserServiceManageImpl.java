package com.chendong.manage.impl;

import java.util.Map;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.chendong.common.api.BaseApiService;
import com.chendong.common.redis.BaseRedisService;
import com.chendong.common.token.TokenUtils;
import com.chendong.common.util.DateUtils;
import com.chendong.common.util.MD5Util;
import com.chendong.constants.Constants;
import com.chendong.constants.DBTableName;
import com.chendong.constants.MQInterfaceType;
import com.chendong.dao.UserDao;
import com.chendong.entity.UserEntity;
import com.chendong.manage.UserServiceManage;
import com.chendong.mq.producer.RegisterMailboxProducer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceManageImpl extends BaseApiService implements UserServiceManage{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RegisterMailboxProducer registerMailboxProducer;
	
	@Value("${messages.queue}")
	private String MAIL_QUEUE;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private BaseRedisService baseRedisService;
	
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

	@Override
	public Map<String , Object> login(UserEntity userEntity) {
		//往数据库中进行查找数据
		String phone = userEntity.getPhone();
		String password = userEntity.getPassword();
		UserEntity userEntityNew = userDao.getUserPhoneAndPwd(phone,md5PassSalt(phone,password));
		if(userEntityNew == null){
			return setResultError("账号或密码错误");
		}
		//生成对应的token
		String token = tokenUtils.getToken();
		Long userId = userEntityNew.getId();
		//key为自定义令牌，用户的userId作为value 存放在redis中
		baseRedisService.set(token, userId + "", Constants.TERMVALIDITY);
		//返回token
		return setResultData(token);
	}

	@Override
	public Map<String, Object> getUser(String token) {
		// 从redis中查找到userId
		String userId = baseRedisService.get(token);
		if(StringUtils.isEmpty(userId)){
			return setResultError("用户已经过期！");
		}
		Long newUserIdl = Long.parseLong(userId);
		UserEntity userInfo = userDao.getUserInfo(newUserIdl);
		userInfo.setPassword(null);
		return setResultData(userInfo);
	}

}
