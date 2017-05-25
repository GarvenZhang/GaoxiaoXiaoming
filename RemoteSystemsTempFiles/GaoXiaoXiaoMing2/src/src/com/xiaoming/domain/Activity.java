package com.xiaoming.domain;

import java.util.Date;
import java.util.Set;
/**
 * 活动类
 * 
 * @author LiuRui
 *
 */
public class Activity {
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 创建时间s
	 */
	private Date createTime;
	/**
	 * 活动名称
	 */
	private String title;
	/**
	 * 活动内容
	 */
	private String content;
	/**
	 * 所属社团
	 */
	private Organization organization;
	/**
	 * 创建者
	 */
	private Member creater;
	/**
	 * 报名情况
	 */
	private Set<ActivityEnroll> activityEnrolls;
	/**
	 * 应填的信息，json格式
	 */
	private String info;
	
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
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	/**
	 * @return the creater
	 */
	public Member getCreater() {
		return creater;
	}
	/**
	 * @param creater the creater to set
	 */
	public void setCreater(Member creater) {
		this.creater = creater;
	}
	/**
	 * @return the activityEnrolls
	 */
	public Set<ActivityEnroll> getActivityEnrolls() {
		return activityEnrolls;
	}
	/**
	 * @param activityEnrolls the activityEnrolls to set
	 */
	public void setActivityEnrolls(Set<ActivityEnroll> activityEnrolls) {
		this.activityEnrolls = activityEnrolls;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
