package com.xiaoming.domain;

import java.util.Date;

/**
 * 工作动态
 * 
 * @author Yunfei
 *
 */
public class DynamicState {
	/**
	 * 主键，id
	 */
	private Long id;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 操作者
	 */
	private Member member;
	/**
	 * 所属的项目
	 */
	private Project project;
	/**
	 * 操作的任务
	 */
	private Assignment assignment;
	/**
	 * 操作的时间
	 */
	private Date operaTime;
	
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the member
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * @param member
	 *            the member to set
	 */
	public void setMember(Member member) {
		this.member = member;
	}

	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project
	 *            the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * @return the assignment
	 */
	public Assignment getAssignment() {
		return assignment;
	}

	/**
	 * @param assignment
	 *            the assignment to set
	 */
	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	/**
	 * @return the operaTime
	 */
	public Date getOperaTime() {
		return operaTime;
	}

	/**
	 * @param operaTime the operaTime to set
	 */
	public void setOperaTime(Date operaTime) {
		this.operaTime = operaTime;
	}

}
