package com.xiaoming.domain;

import java.util.Date;
/**
 * 活动报名类
 * 
 * @author LiuRui
 * @author Yunfei
 *
 */
public class ActivityEnroll {
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 报名成员
	 */
	private Member member;
	/**
	 * 报名的活动
	 */
	private Activity activity;
	/**
	 * 报名时间
	 */
	private Date enrollTime;
	/**
	 * 已填的信息，json格式
	 */
	private String info;
	/**
	 * 是否已经处理
	 */
	private Boolean isHandled = false;
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

	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	/**
	 * @return the activity
	 */
	public Activity getActivity() {
		return activity;
	}
	/**
	 * @param activity the activity to set
	 */
	public void setActivity(Activity activity) {
		this.activity = activity; 
	}

	public Date getEnrollTime() {
		return enrollTime;
	}
	public void setEnrollTime(Date enrollTime) {
		this.enrollTime = enrollTime;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Boolean getIsHandled() {
		return isHandled;
	}
	public void setIsHandled(Boolean isHandled) {
		this.isHandled = isHandled;
	} 
}
