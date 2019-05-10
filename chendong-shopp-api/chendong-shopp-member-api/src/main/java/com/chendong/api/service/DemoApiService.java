package com.chendong.api.service;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/demo")
public interface DemoApiService {

	/**
	 * 服务demo
	 * @return
	 */
	@GetMapping("/demo")
	public Map<String,Object> demo();
	
	@GetMapping("/setkey")
	public Map<String,Object> setkey(String key , String value);
	
	@GetMapping("/getkey")
	public Map<String,Object> getkey(String key);
}
