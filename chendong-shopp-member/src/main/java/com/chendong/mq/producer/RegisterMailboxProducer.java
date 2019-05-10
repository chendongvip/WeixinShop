package com.chendong.mq.producer;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * 往消息服务 推送 邮件信息
 * @author Administrator
 *
 */
@Service("registerMailboxProducer")
public class RegisterMailboxProducer {

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	public void send(Destination destination , String json){
		jmsMessagingTemplate.convertAndSend(destination, json);
	}
}
