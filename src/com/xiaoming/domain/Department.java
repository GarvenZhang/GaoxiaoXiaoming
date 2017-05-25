package com.xiaoming.domain;

/**
 * 部门类 一个社团可以拥有多个部门 一个部门可以拥有多个成员
 * 
 * @author Yunfei
 *
 */
public class Department {
	/**
	 * 主键，id
	 */
	private Long id;
	/**
	 * 部门名称
	 */
	private String name;
	/*
	 * 多对一映射
	 */
	/**
	 * 所属社团 部门 n:1 社团
	 */
	private Organization orgBelong;

	/*
	 * 方法区
	 */
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the orgBelongTo
	 */
	public Organization getOrgBelong() {
		return orgBelong;
	}

	/**
	 * @param orgBelong
	 *            the orgBelong to set
	 */
	public void setOrgBelong(Organization orgBelong) {
		this.orgBelong = orgBelong;
	}

}
