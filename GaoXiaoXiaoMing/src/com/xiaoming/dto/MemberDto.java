package com.xiaoming.dto;

import com.xiaoming.domain.Member;
import com.xiaoming.util.JsonIgnore;

/**
 * 成员的DTO
 * 
 * @author Yunfei
 *
 */
public class MemberDto {
	/**
	 * id
	 */
	private String id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 是否已读
	 */
	private boolean isRead;
	/**
	 * 是否完成
	 */
	private boolean isFinished;
	/**
	 * 延期天数
	 */
	private long delay;
	/**
	 * 是否同意请假
	 */
	private boolean isAgree;
	/**
	 * 届数
	 */
	private String edition;
	/**
	 * 部门
	 */
	private String department;
	/**
	 * 职位
	 */
	private String position;
	/**
	 * 联系电话
	 */
	private String phoneNumber;
	/**
	 * 组织
	 */
	private String organization;
	/**
	 * 排序方式， 0：按姓名排序 1：部门排序
	 */
	private int sort;
	/**
	 * 状态
	 */
	private int state;
	/**
	 * 专业
	 */
	private String major;
	/**
	 * 年级
	 */
	private String grade;

	private int pageSize;
	private int pageNum;

	public MemberDto() {

	}

	public MemberDto(Member member) {
		id = member.getId().toString();
		name = member.getUser().getRealName();
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

	public boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(boolean isRead) {
		this.isRead = isRead;
	}

	public boolean getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public boolean getIsAgree() {
		return isAgree;
	}

	public void setIsAgree(boolean isAgree) {
		this.isAgree = isAgree;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@JsonIgnore
	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	@JsonIgnore
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@JsonIgnore
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
