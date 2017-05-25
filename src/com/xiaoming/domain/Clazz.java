package com.xiaoming.domain;

import java.util.Set;

/**
 * 班级类
 * 
 * @author Yunfei
 *
 */
public class Clazz {
	/**
	 * 主键，id
	 */
	private Long id;
	/**
	 * 班级名
	 */
	private String name;
	/**
	 * 年级
	 */
	private Grade grade;
	/**
	 * 专业
	 */
	private Major major;
//	/**
//	 * 拥有的用户的集合
//	 */
//	private Set<User> users;

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
	 * @return the grade
	 */
	public Grade getGrade() {
		return grade;
	}

	/**
	 * @param grade
	 *            the grade to set
	 */
	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	/**
	 * @return the major
	 */
	public Major getMajor() {
		return major;
	}

//	/**
//	 * @return the users
//	 */
//	public Set<User> getUsers() {
//		return users;
//	}
//
//
//	/**
//	 * @param users the users to set
//	 */
//	public void setUsers(Set<User> users) {
//		this.users = users;
//	}

	/**
	 * @param major the major to set
	 */
	public void setMajor(Major major) {
		this.major = major;
	}


}
