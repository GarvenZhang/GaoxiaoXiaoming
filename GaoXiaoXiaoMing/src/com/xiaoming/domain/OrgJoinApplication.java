package com.xiaoming.domain;

import java.util.Date;

/**
 * 社团加入申请类
 * 
 * @author Yunfei
 *
 */
public class OrgJoinApplication {
	/**
	 * 主键，id
	 */
	private Long id;
	/**
	 * 申请用户
	 */
	private User user;
	/**
	 * 申请加入的组织
	 */
	private Organization organization;
	/**
	 * 申请时间
	 */
	private Date applyTime;
	/**
	 * 申请加入的部门
	 */
	private Department department;
	/**
	 * 申请的职位
	 */
	private String position = "";
	/**
	 * 是否通过
	 */
	private Boolean isPassed = false;
	/**
	 * 是否處理
	 */
	private Boolean isHandled = false;
	/**
	 * 原因
	 */
	private String reason = "";
	/**
	 * 处理时间
	 */
	private Date handleTime;

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
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the organization
	 */
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * @param organization
	 *            the organization to set
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/**
	 * @return the applyTime
	 */
	public Date getApplyTime() {
		return applyTime;
	}

	/**
	 * @param applyTime
	 *            the applyTime to set
	 */
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	/**
	 * @return the isPassed
	 */
	public Boolean getIsPassed() {
		return isPassed;
	}

	/**
	 * @param isPassed
	 *            the isPassed to set
	 */
	public void setIsPassed(Boolean isPassed) {
		this.isPassed = isPassed;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the handleTime
	 */
	public Date getHandleTime() {
		return handleTime;
	}

	/**
	 * @param handleTime
	 *            the handleTime to set
	 */
	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Boolean getIsHandled() {
		return isHandled;
	}

	public void setIsHandled(Boolean isHandled) {
		this.isHandled = isHandled;
	}
	
}
