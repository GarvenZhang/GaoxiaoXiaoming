package com.xiaoming.domain;

import java.util.Date;
/**
 * 用户通知类
 * 
 * @author LiuRui
 *
 */
public class UsersMessage {
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 接收者
	 */
	private Member receiver;
	/**
	 * 信息
	 */
	private Message message;
	/**
	 * 是否已读
	 */
	private Boolean isRead = false;
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
	 * @return the receiver
	 */
	public Member getReceiver() {
		return receiver;
	}
	/**
	 * @param receiver the receiver to set
	 */
	public void setReceiver(Member receiver) {
		this.receiver = receiver;
	}
	/**
	 * @return the isRead
	 */
	public Boolean getIsRead() {
		return isRead;
	}
	/**
	 * @param isRead the isRead to set
	 */
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}
	/**
	 * @return the message
	 */
	public Message getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(Message message) {
		this.message = message;
	}
	
	
}
