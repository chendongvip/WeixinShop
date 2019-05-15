package com.chendong.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.chendng.api.service.ItemService;

@FeignClient("commodity")
public interface ItemFeign extends ItemService {

}
