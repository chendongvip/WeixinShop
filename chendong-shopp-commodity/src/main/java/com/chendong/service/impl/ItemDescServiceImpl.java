package com.chendong.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chendng.api.service.ItemDescService;
import com.chendng.entity.ItemDescEntity;
import com.chendong.common.api.BaseApiService;
import com.chendong.dao.ItemDescDao;



@RestController
public class ItemDescServiceImpl extends BaseApiService implements ItemDescService {
	@Autowired
	private ItemDescDao itemDescDao;

	@RequestMapping("/getItemDesc")
	public Map<String, Object> getItemDesc(@RequestParam("id") Long id) {
		ItemDescEntity itemDesc = itemDescDao.getItemDesc(id);
		return setResultData(itemDesc);
	}

}
