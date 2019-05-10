package com.chendong.common.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BaseEntity {

	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 创建时间
	 */
	
	private Timestamp created;
	
	/**
	 * 修改时间
	 */
	
	private Timestamp update;
}
