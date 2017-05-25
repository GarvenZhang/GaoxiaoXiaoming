package com.xiaoming.domain;

import java.util.Set;

/**
 * 校区类 校区 1:n 社团 校区 n:1 学校
 * 
 * @author Yunfei
 *
 */
public class Campus {
	/**
	 * 主键 id
	 */
	private Long id;
	/**
	 * 校区名称
	 */
	private String name;
	/**
	 * 所在学校
	 */
	private University university;
	
	private Set<School> schools;

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
	 * @return the school
	 */
	public University getUniversity() {
		return university;
	}

	/**
	 * @param university
	 *            the university to set
	 */
	public void setSchool(University university) {
		this.university = university;
	}

	/**
	 * @return the schools
	 */
	public Set<School> getSchools() {
		return schools;
	}

	/**
	 * @param schools the schools to set
	 */
	public void setSchools(Set<School> schools) {
		this.schools = schools;
	}

	/**
	 * @param university the university to set
	 */
	public void setUniversity(University university) {
		this.university = university;
	}
	
}
