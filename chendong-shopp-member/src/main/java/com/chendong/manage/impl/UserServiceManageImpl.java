package com.chendong.manage.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chendong.api.entity.UserEntity;
import com.chendong.common.util.DateUtils;
import com.chendong.constants.DBTableName;
import com.chendong.dao.UserDao;
import com.chendong.manage.UserServiceManage;

@Service
public class UserServiceManageImpl implements UserServiceManage{

	@Autowired
	private UserDao userDao;
	
	@Override
	public void regist(UserEntity userEntity) {
		userEntity.setCreated(DateUtils.getTimestamp());
		userEntity.setUpdate(DateUtils.getTimestamp());
		userDao.save(userEntity, DBTableName.TABLE_MB_USER);
	}

}
