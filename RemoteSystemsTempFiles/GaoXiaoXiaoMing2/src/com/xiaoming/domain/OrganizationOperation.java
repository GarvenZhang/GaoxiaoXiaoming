package com.xiaoming.domain;

import java.util.Date;

/**
 * 组织的操作日志
 * @author Yunfei
 *
 */
public class OrganizationOperation {
	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 操作的时间
	 */
	private Date time;
	
	/**
	 * 所属组织
	 */
	private Organization organization;
//	/**
//	 * 操作
//	 */
//	private String operate;
//	/**
//	 * 操作的模块
//	 */
//	private String target;
//	/**
//	 * 操作对象
//	 */
//	private String name;
//	/*
//	 * 针对物资
//	 */
//	/**
//	 * 原有数量
//	 */
//	private Integer preCount;
//	/**
//	 * 现有数量
//	 */
//	private Integer count;
//
//	/**
//	 * 描述
//	 */
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
//
//	public String getOperate() {
//		return operate;
//	}
//
//	public void setOperate(String operate) {
//		this.operate = operate;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public Integer getPreCount() {
//		return preCount;
//	}
//
//	public void setPreCount(Integer preCount) {
//		this.preCount = preCount;
//	}
//
//	public Integer getCount() {
//		return count;
//	}
//
//	public void setCount(Integer count) {
//		this.count = count;
//	}
//
//	public String getTarget() {
//		return target;
//	}
//
//	public void setTarget(String target) {
//		this.target = target;
//	}
//	
}
