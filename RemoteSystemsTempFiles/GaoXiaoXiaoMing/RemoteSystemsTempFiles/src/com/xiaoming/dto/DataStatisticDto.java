package com.xiaoming.dto;

import com.xiaoming.util.JsonIgnore;

/**
 * 数据统计
 * 
 * @author Yunfei
 *
 */
public class DataStatisticDto {
	private String id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 部门
	 */
	private String department;
	/**
	 * 发布的工作数量
	 */
	private long published;
	/**
	 * 收到的工作数量
	 */
	private long received;
	/**
	 * 完成的工作数量
	 */
	private long finished;
	/**
	 * 逾期的工作数量
	 */
	private long outOfDate;
	/**
	 * 未完成的工作数量
	 */
	private long imperfect;
	/**
	 * 请假次数
	 */
	private long absence;
	
	private int daysAgo;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public long getPublished() {
		return published;
	}

	public void setPublished(long published) {
		this.published = published;
	}

	public long getReceived() {
		return received;
	}

	public void setReceived(long received) {
		this.received = received;
	}

	public long getFinished() {
		return finished;
	}

	public void setFinished(long finished) {
		this.finished = finished;
	}

	public long getOutOfDate() {
		return outOfDate;
	}

	public void setOutOfDate(long outOfDate) {
		this.outOfDate = outOfDate;
	}

	public long getImperfect() {
		return imperfect;
	}

	public void setImperfect(long imperfect) {
		this.imperfect = imperfect;
	}

	public long getAbsence() {
		return absence;
	}

	public void setAbsence(long absence) {
		this.absence = absence;
	}

	private int pageSize;
	private int pageNum;
	private String error;

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
	
	@JsonIgnore
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonIgnore
	public int getDaysAgo() {
		return daysAgo;
	}

	public void setDaysAgo(int daysAgo) {
		this.daysAgo = daysAgo;
	}

}
