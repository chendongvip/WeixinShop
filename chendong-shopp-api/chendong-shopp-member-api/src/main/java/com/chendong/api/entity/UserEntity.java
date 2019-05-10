package com.chendong.api.entity;

import com.chendong.common.entity.BaseEntity;

import lombok.Data;

@Data
public class UserEntity extends BaseEntity{

	private String userName;
	private String password;
	private String phone;
	private String email;
}