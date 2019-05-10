package com.chendong.adapter;

import com.alibaba.fastjson.JSONObject;

/**
 * 所有消息都会交给他进行转发
 * @author Administrator
 *
 */
public interface MessageAdapter {

	//接受到mq中传来的消息
	public void distribute(JSONObject jsonObject);
}
