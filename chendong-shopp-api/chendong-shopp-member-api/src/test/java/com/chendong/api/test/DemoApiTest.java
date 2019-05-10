package com.chendong.api.test;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/demoTest")
public interface DemoApiTest {

	/**
	 * demoTest(测试demo)
	 */
	@GetMapping("/setkey")
	public Map<String,Object> setkey(String key , String value);
	
	@GetMapping("/getkey")
	public Map<String,Object> getkey(String key);
}
