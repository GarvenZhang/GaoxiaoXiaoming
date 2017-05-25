package com.xiaoming.domain;

import java.util.Set;

/**
 * 成员类 （用户加入社团后，成为成员）
 * 
 * @author Yunfei
 *
 */
public class Member {
	/**
	 * 主键，id
	 */
	private Long id;
	/**
	 * 职务
	 */
	private String position;
	/**
	 * 届
	 */
	private String edition;
	/**
	 * 是否加入
	 */
	private Byte state;

	/*
	 * 映射
	 */
	/**
	 * 对应的用户
	 */
	private User user;
	/**
	 * 部门
	 */
	private Department department;
	/**
	 * 发出的请假
	 */
	private Set<Absence> publishedAbsences;
	/**
	 * 收到的请假信息
	 */
	private Set<AbsenceApply> receivedAbsences;
	/**
	 * 发布的工作
	 */
	private Set<Assignment> publishedAssignment;
	/**
	 * 收到的工作
	 */
	private Set<AssignmentMember> receivedAssignment;

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
	 * @return the publishedAbsences
	 */
	public Set<Absence> getPublishedAbsences() {
		return publishedAbsences;
	}

	/**
	 * @param publishedAbsences
	 *            the publishedAbsences to set
	 */
	public void setPublishedAbsences(Set<Absence> publishedAbsences) {
		this.publishedAbsences = publishedAbsences;
	}

	/**
	 * @return the receivedAbsences
	 */
	public Set<AbsenceApply> getReceivedAbsences() {
		return receivedAbsences;
	}

	/**
	 * @param receivedAbsences
	 *            the receivedAbsences to set
	 */
	public void setReceivedAbsences(Set<AbsenceApply> receivedAbsences) {
		this.receivedAbsences = receivedAbsences;
	}

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
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the edition
	 */
	public String getEdition() {
		return edition;
	}

	/**
	 * @param edition
	 *            the edition to set
	 */
	public void setEdition(String edition) {
		this.edition = edition;
	}

	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * @return the state
	 */
	public Byte getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(Byte state) {
		this.state = state;
	}

	public Set<Assignment> getPublishedAssignment() {
		return publishedAssignment;
	}

	public void setPublishedAssignment(Set<Assignment> publishedAssignment) {
		this.publishedAssignment = publishedAssignment;
	}

	public Set<AssignmentMember> getReceivedAssignment() {
		return receivedAssignment;
	}

	public void setReceivedAssignment(Set<AssignmentMember> receivedAssignment) {
		this.receivedAssignment = receivedAssignment;
	}


}
