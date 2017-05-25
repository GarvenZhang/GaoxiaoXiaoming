package com.xiaoming.dto;

import java.util.ArrayList;

import com.xiaoming.domain.Member;
import com.xiaoming.domain.Unread;

public class UnreadDto {
	/**
	 * 主键id,与成员（Member）id对应
	 */
	private String id;
	/**
	 * 请假信息
	 */
	private int absence;
	/**
	 * 活动信息
	 */
	private int activity;
	/**
	 * 任务信息
	 */
	private int assignment;
	/**
	 * 通知
	 */
	private int message;
	
	public UnreadDto() {
		// TODO Auto-generated constructor stub
	}
	
	public UnreadDto(Unread unread){
		id = unread.getId().toString();
		absence = unread.getAbsence();
		activity = unread.getActivity();
		assignment = unread.getAssignment();
		message = unread.getMessage();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public int getAbsence() {
		return absence;
	}
	public void setAbsence(int absence) {
		this.absence = absence;
	}
	public int getActivity() {
		return activity;
	}
	public void setActivity(int activity) {
		this.activity = activity;
	}
	public int getAssignment() {
		return assignment;
	}
	public void setAssignment(int assignment) {
		this.assignment = assignment;
	}
	public int getMessage() {
		return message;
	}
	public void setMessage(int message) {
		this.message = message;
	}

}
