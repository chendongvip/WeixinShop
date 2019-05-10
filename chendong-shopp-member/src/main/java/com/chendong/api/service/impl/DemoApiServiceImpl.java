package com.chendong.api.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.chendong.api.service.DemoApiService;
import com.chendong.common.api.BaseApiService;
import com.chendong.common.redis.BaseRedisService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DemoApiServiceImpl extends BaseApiService implements DemoApiService{

	@Autowired
	private BaseRedisService baseRedisService;
	
	@Override
	public Map<String,Object> demo() {
		log.info("this is demo");
		return setResultSuccess();
	}
	
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
