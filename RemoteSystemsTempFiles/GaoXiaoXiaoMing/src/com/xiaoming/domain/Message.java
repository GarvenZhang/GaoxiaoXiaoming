package com.xiaoming.domain;

import java.util.Date;
import java.util.Set;

/**
 * 通知类
 * 
 * @author LiuRui
 *
 */
public class Message {
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 通知内容
	 */
	private String content;
	/**
	 * 创建时间
	 */
	private Date publishTime;
	/**
	 * 最后更新的时间
	 */
	private Date updateTime;
	/**
	 * 创建者
	 */
	private Member publisher;
	/**
	 * 是否刪除了
	 */
	private Boolean deleted;
	/**
	 * 用户阅读情况
	 */
	private Set<UsersMessage> usersMessage;

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
	 * @return the publishTime
	 */
	public Date getPublishTime() {
		return publishTime;
	}

	/**
	 * @param publishTime
	 *            the publishTime to set
	 */
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	/**
	 * @return the publisher
	 */
	public Member getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher
	 *            the publisher to set
	 */
	public void setPublisher(Member publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the usersMessage
	 */
	public Set<UsersMessage> getUsersMessage() {
		return usersMessage;
	}

	/**
	 * @param usersMessage
	 *            the usersMessage to set
	 */
	public void setUsersMessage(Set<UsersMessage> usersMessage) {
		this.usersMessage = usersMessage;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}
