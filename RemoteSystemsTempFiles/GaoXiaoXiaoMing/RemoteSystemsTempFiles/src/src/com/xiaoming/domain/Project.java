package com.xiaoming.domain;

import java.util.Date;
import java.util.Set;

/**
 * 项目类，社团管理员可以创建项目
 * 
 * @author Yunfei
 *
 */
public class Project {
	/**
	 * 主键，id
	 */
	private Long id;
	/**
	 * 项目名称
	 */
	private String name;
	/**
	 * 创建时间
	 */
	private Date publishTime;

	/*
	 * 映射
	 */
	/**
	 * 创建者
	 */
	private Member publisher;
	/**
	 * 所属社团
	 */
	private Organization orgBelong;
	/**
	 * 任务集合
	 */
	private Set<Assignment> assignments;
	/**
	 * 动态
	 */
	private Set<DynamicState> dynamicState;
	
	/*
	 * Getter and Setter
	 */
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public Project(Long id) {
		super();
		this.id = id;
	}

	/**
	 * 
	 */
	public Project() {
		super();
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
	 * @return the publishTime
	 */
	public Date getPublishTime() {
		return publishTime;
	}

	/**
	 * @param publishTime
	 *            the publishTime to set
	 */
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	/**
	 * @return the publisher
	 */
	public Member getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher
	 *            the publisher to set
	 */
	public void setPublisher(Member publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the orgBelong
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

	/**
	 * @return the assignments
	 */
	public Set<Assignment> getAssignments() {
		return assignments;
	}

	/**
	 * @param assignments
	 *            the assignments to set
	 */
	public void setAssignments(Set<Assignment> assignments) {
		this.assignments = assignments;
	}

	/**
	 * @return the dynamicState
	 */
	public Set<DynamicState> getDynamicState() {
		return dynamicState;
	}

	/**
	 * @param dynamicState the dynamicState to set
	 */
	public void setDynamicState(Set<DynamicState> dynamicState) {
		this.dynamicState = dynamicState;
	}

}
