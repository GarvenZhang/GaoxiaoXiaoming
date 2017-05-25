package com.xiaoming.domain;

import java.util.Date;

import com.xiaoming.base.Role;

/**
 * 系统消息
 * 
 * @author Yunfei
 *
 */
public class SystemMessage {

	/**
	 * 主键，id
	 */
	private Long id;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 发布时间
	 */
	private Date publishTime;
	/**
	 * 是否发给所有人
	 */
	private Boolean toAll;
	/**
	 * 发布对象:
	 */
	private Role target;
	/**
	 * 是否已删除
	 */
	private Boolean isDeleted;

	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Boolean getToAll() {
		return toAll;
	}

	public void setToAll(Boolean toAll) {
		this.toAll = toAll;
	}

	public Role getTarget() {
		return target;
	}

	public void setTarget(Role target) {
		this.target = target;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
