package com.xiaoming.domain;

import java.util.Date;

/**
 * 请假申请，成员与请假的中间表
 * @author Yunfei
 *
 */
public class AbsenceApply {
	/**
	 * 主键，id
	 */
	private Long id;
	/**
	 * 接收者
	 */
	private Member receiver;
	/**
	 * 是否同意
	 */
	private Boolean isAgree = false;
	/**
	 * 处理时间
	 */
	private Date handleTime;
	/**
	 * 是否已经处理
	 */
	private Boolean isHandled = false;
	/**
	 * 对应的请假信息，多对一映射
	 */
	private Absence absence;
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
	 * @return the isAgree
	 */
	public Boolean getIsAgree() {
		return isAgree;
	}
	/**
	 * @param isAgree the isAgree to set
	 */
	public void setIsAgree(Boolean isAgree) {
		this.isAgree = isAgree;
	}
	/**
	 * @return the handleTime
	 */
	public Date getHandleTime() {
		return handleTime;
	}
	/**
	 * @param handleTime the handleTime to set
	 */
	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}
	/**
	 * @return the absence
	 */
	public Absence getAbsence() {
		return absence;
	}
	/**
	 * @param absence the absence to set
	 */
	public void setAbsence(Absence absence) {
		this.absence = absence;
	}
	/**
	 * @return the isHandled
	 */
	public Boolean getIsHandled() {
		return isHandled;
	}
	/**
	 * @param isHandled the isHandled to set
	 */
	public void setIsHandled(Boolean isHandled) {
		this.isHandled = isHandled;
	}
	
}
