package com.xiaoming.domain;

import java.util.Set;

/**
 * 学院类
 * 一个校区多个学院
 * @author Yunfei
 *
 */
public class School {
	/**
	 * 主键，id
	 */
	private Long id;
	/**
	 * 学院名
	 */
	private String name;
	/**
	 * 所在校区
	 */
	private Campus campus;
	/**
	 * 专业集合
	 */
	private Set<Major> majors;
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
	/**
	 * @return the campus
	 */
	public Campus getCampus() {
		return campus;
	}
	/**
	 * @param campus the campus to set
	 */
	public void setCampus(Campus campus) {
		this.campus = campus;
	}
	/**
	 * @return the majors
	 */
	public Set<Major> getMajors() {
		return majors;
	}
	/**
	 * @param majors the majors to set
	 */
	public void setMajors(Set<Major> majors) {
		this.majors = majors;
	}
	
	
}
