package com.xiaoming.domain;

import java.util.Set;

/**
 * 专业类
 * @author Yunfei
 *
 */
public class Major {
	/**
	 * 主键，id
	 */
	private Long id;
	/**
	 * 专业名
	 */
	private String name;
	/**
	 * 所在学院
	 */
	private University university;


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
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
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}
	
}
