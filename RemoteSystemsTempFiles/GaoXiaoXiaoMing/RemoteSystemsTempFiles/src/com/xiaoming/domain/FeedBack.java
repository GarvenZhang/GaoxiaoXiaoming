package com.xiaoming.domain;

import java.util.Date;
/**
 * 反馈类
 * 
 * @author LiuRui
 *
 */
public class FeedBack {
	/**
	 * 主键，id
	 */
	private Long id;
	/**
	 * 提交用户
	 */
	private User user;
	/**
	 * 内容
	 */
	private String feedBackContent;
	/**
	 * 提交时间
	 */
	private Date submitTime;
	/**
	 * 回复时间
	 */
	private Date replyTime;
	/**
	 * 回复
	 */
	private String reply;
	
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
	 * @return the users
	 */
	public User getUsers() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUsers(User user) {
		this.user = user;
	}
	/**
	 * @return the feedBackContent
	 */
	public String getFeedBackContent() {
		return this.feedBackContent;
	}
	/**
	 * @param feedBackContent the feedBackContent to set
	 */
	public void setFeedBackContent(String feedBackContent) {
		this.feedBackContent = feedBackContent;
	}
	/**
	 * @return the submitTime
	 */
	public Date getSubmitTime() {
		return submitTime;
	}
	/**
	 * @param submitTime the submitTime to set
	 */
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	/**
	 * @return the replyTime
	 */
	public Date getReplyTime() {
		return replyTime;
	}
	/**
	 * @param replyTime the replyTime to set
	 */
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the reply
	 */
	public String getReply() {
		return reply;
	}
	/**
	 * @param reply the reply to set
	 */
	public void setReply(String reply) {
		this.reply = reply;
	}
	
	

}
