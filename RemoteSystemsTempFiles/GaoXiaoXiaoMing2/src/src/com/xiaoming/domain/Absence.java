package com.xiaoming.domain;

import java.util.Date;
import java.util.Set;

/**
 * 请假
 * 
 * @author Yunfei
 *
 */
public class Absence {
	/**
	 * 主键，id
	 */
	private Long id;
	/**
	 * 请假内容
	 */
	private String content;
	/**
	 * 请假者
	 */
	private Member member;
	/**
	 * 要请假的时间
	 */
	private Date absenceTime;
	/**
	 * 请假结束时间
	 */
	private Date endTime;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后更新时间
	 */
	private Date updateTime;

	/**
	 * 一对多映射
	 */
	private Set<AbsenceApply> absenceApply;

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
	 * @return the member
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * @param member
	 *            the member to set
	 */
	public void setMember(Member member) {
		this.member = member;
	}

	/**
	 * @return the absenceTime
	 */
	public Date getAbsenceTime() {
		return absenceTime;
	}

	/**
	 * @param absenceTime
	 *            the absenceTime to set
	 */
	public void setAbsenceTime(Date absenceTime) {
		this.absenceTime = absenceTime;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the absenceApply
	 */
	public Set<AbsenceApply> getAbsenceApply() {
		return absenceApply;
	}

	/**
	 * @param absenceApply
	 *            the absenceApply to set
	 */
	public void setAbsenceApply(Set<AbsenceApply> absenceApply) {
		this.absenceApply = absenceApply;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
