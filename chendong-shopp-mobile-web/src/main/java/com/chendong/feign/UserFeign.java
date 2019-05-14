package com.chendong.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.chendong.api.service.UserService;

@FeignClient("member")
public interface UserFeign extends UserService{

}
