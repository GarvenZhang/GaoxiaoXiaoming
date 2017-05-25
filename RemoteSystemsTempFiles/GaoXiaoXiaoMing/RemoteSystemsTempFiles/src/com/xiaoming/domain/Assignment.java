package com.xiaoming.domain;

import java.util.Date;
import java.util.Set;

/**
 * 任务类
 * 
 * @author Yunfei
 *
 */
public class Assignment {
	/**
	 * 主键，id
	 */
	private Long id;
	/**
	 * 任务内容
	 */
	private String content;
	/**
	 * 任务发布时间
	 */
	private Date publishDate;
	/**
	 * 截止日期
	 */
	private Date deadline;

	/*
	 * 映射
	 */
	/**
	 * 创建者
	 */
	private Member publisher;
	/**
	 * 所属的项目
	 */
	private Project projectBelongTo;
	private Boolean isValid;
	/**
	 * 任务-成员的中间表
	 */
	private Set<AssignmentMember> members;
	/**
	 * 最后一次更新时间
	 */
	private Date updateTime;
	
	/*
	 * Getter and Setter
	 */	
	
	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the publisher
	 */
	public Member getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(Member publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the isValid
	 */
	public Boolean getIsValid() {
		return isValid;
	}

	/**
	 * @param isValid the isValid to set
	 */
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	/**
	 * @return the projectBelongTo
	 */
	public Project getProjectBelongTo() {
		return projectBelongTo;
	}

	/**
	 * @param projectBelongTo the projectBelongTo to set
	 */
	public void setProjectBelongTo(Project projectBelongTo) {
		this.projectBelongTo = projectBelongTo;
	}

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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the publishDate
	 */
	public Date getPublishDate() {
		return publishDate;
	}

	/**
	 * @param publishDate
	 *            the publishDate to set
	 */
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	/**
	 * @return the deadline
	 */
	public Date getDeadline() {
		return deadline;
	}

	/**
	 * @param deadline
	 *            the deadline to set
	 */
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	/**
	 * @return the members
	 */
	public Set<AssignmentMember> getMembers() {
		return members;
	}

	/**
	 * @param members the members to set
	 */
	public void setMembers(Set<AssignmentMember> members) {
		this.members = members;
	}

}
