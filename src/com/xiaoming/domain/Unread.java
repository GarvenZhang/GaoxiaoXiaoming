package com.xiaoming.domain;

import com.xiaoming.constants.Constants;

/**
 * 统计未读消息数量
 * 
 * @author Yunfei
 *
 */
public class Unread {
	/**
	 * 主键id,与成员（Member）id对应
	 */
	private Long id;
	/**
	 * 对于对应的用户
	 */
	private Member member;
	/**
	 * 请假信息
	 */
	private Integer absence;
	/**
	 * 活动信息
	 */
	private Integer activity;
	/**
	 * 任务信息
	 */
	private Integer assignment;
	/**
	 * 通知
	 */
	private Integer message;
	
	public Unread(){
		this.absence = 0;
		this.activity = 0;
		this.assignment = 0;
		this.message = 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAbsence() {
		return absence;
	}

	public void setAbsence(Integer absence) {
		this.absence = absence;
	}

	public Integer getActivity() {
		return activity;
	}

	public void setActivity(Integer activity) {
		this.activity = activity;
	}

	public Integer getAssignment() {
		return assignment;
	}

	public void setAssignment(Integer assignment) {
		this.assignment = assignment;
	}

	public Integer getMessage() {
		return message;
	}

	public void setMessage(Integer message) {
		this.message = message;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public void addActivity() {
		activity++;
	}

	public void addAbsence() {
		absence++;
	}

	public void addAssignment() {
		assignment++;
	}

	public void addMessage() {
		message++;
	}

	public void add(int type) {
		switch (type) {
		case Constants.UNREAD_ALL:
			addActivity();
			addAbsence();
			addAssignment();
			addMessage();
			break;
		case Constants.UNREAD_ACTIVITY:
			addActivity();
			break;
		case Constants.UNREAD_ABSENCE:
			addAbsence();
			break;
		case Constants.UNREAD_ASSIGNMENT:
			addAssignment();
			break;
		case Constants.UNREAD_MESSAGE:
			addMessage();
			break;
		default:
			break;
		}
	}

	public void clearActivity() {
		activity = 0;
	}

	public void clearAbsence() {
		absence = 0;
	}

	public void clearAssignment() {
		assignment = 0;
	}

	public void clearMessage() {
		message = 0;
	}


	public void clear(int type) {
		switch (type) {
		case Constants.UNREAD_ALL:
			clearActivity();
			clearAbsence();
			clearAssignment();
			clearMessage();
			break;
		case Constants.UNREAD_ACTIVITY:
			clearActivity();
			break;
		case Constants.UNREAD_ABSENCE:
			clearAbsence();
			break;
		case Constants.UNREAD_ASSIGNMENT:
			clearAssignment();
			break;
		case Constants.UNREAD_MESSAGE:
			clearMessage();
			break;
		default:
			break;
		}
	}

}
