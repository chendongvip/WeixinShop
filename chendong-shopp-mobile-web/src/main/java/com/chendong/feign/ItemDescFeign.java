package com.chendong.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.chendng.api.service.ItemDescService;

@FeignClient("commodity")
public interface ItemDescFeign extends ItemDescService {

}
