package com.xiaoming.domain;

import java.util.Set;

/**
 * 学校类 学校 1:n 校区
 * 
 * @author Yunfei
 *
 */
public class University {
	/**
	 * 主键，id
	 */
	private Long id;
	/**
	 * 学校名称
	 */
	private String name;

	/**
	 * @return 主键 id
	 */
	public Long getId() {
		return id;
	}
	
	/*
	 * 一对多映射
	 */
	public Set<Campus> campus;

	/**
	 * @param id 主键 id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the 学校名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name 学校名称
	 *            the 学校名称 to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the 校区列表
	 */
	public Set<Campus> getCampus() {
		return campus;
	}

	/**
	 * @param campus 校区列表 the campus to set
	 */
	public void setCampus(Set<Campus> campus) {
		this.campus = campus;
	}
	
	

}
