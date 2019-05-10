package com.chendong.api.test.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.chendong.api.test.DemoApiTest;
import com.chendong.common.api.BaseApiService;
import com.chendong.common.redis.BaseRedisService;

@RestController
public class DemoApiTestImpl extends BaseApiService implements DemoApiTest{
	
	@Autowired
	private BaseRedisService baseRedisService;

	@Override
	public Map<String, Object> setkey(String key, String value) {
		baseRedisService.setString(key, value);
		return setResultSuccess();
	}

	@Override
	public Map<String, Object> getkey(String key) {
		String value = baseRedisService.get(key);
		return setResultData(value);
	}

}
