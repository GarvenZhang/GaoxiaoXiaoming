package com.xiaoming.dto;

import java.util.Date;

import com.xiaoming.domain.ActivityEnroll;

/**
 * 活动报名
 * @author Yunfei
 *
 */
public class ActivityEnrollDto {
	private String id;
	/**
	 * 这条报名信息对应的成员姓名
	 */
	private String name;
	/**
	 * 报名所填的信息
	 */
	private String info;
	/**
	 * 是否已经处理
	 */
	private boolean isHandled;
	/**
	 * 报名的时间
	 */
	private Date enrollTime;
	
	public ActivityEnrollDto(){
		
	}
	
	public ActivityEnrollDto(ActivityEnroll enroll){
		id = enroll.getId().toString();
		name = enroll.getMember().getUser().getRealName();
		info = enroll.getInfo();
		isHandled = enroll.getIsHandled()==null?true:enroll.getIsHandled();
		enrollTime = enroll.getEnrollTime();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public boolean isHandled() {
		return isHandled;
	}
	public void setHandled(boolean isHandled) {
		this.isHandled = isHandled;
	}

	public Date getEnrollTime() {
		return enrollTime;
	}

	public void setEnrollTime(Date enrollTime) {
		this.enrollTime = enrollTime;
	}
}
