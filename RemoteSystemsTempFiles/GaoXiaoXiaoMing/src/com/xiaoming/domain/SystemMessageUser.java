package com.xiaoming.domain;

/**
 * 系统消息-用户 中间表
 * @author Yunfei
 *
 */
public class SystemMessageUser {
	/**
	 * 主键，id
	 */
	private Long id;
	/**
	 * 消息
	 */
	private SystemMessage message;
	/**
	 * 用户
	 */
	private User user;
	/**
	 * 是否已读
	 */
	private Boolean read;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getRead() {
		return read;
	}
	public void setRead(Boolean read) {
		this.read = read;
	}
}
