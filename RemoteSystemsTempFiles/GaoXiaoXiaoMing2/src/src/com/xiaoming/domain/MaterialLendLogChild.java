package com.xiaoming.domain;

import java.util.Date;

import com.xiaoming.util.JsonIgnore;

/**
 * 出借记录的字记录
 * 
 * @author Yunfei
 *
 */
public class MaterialLendLogChild {
	/**
	 * 主键，id
	 */
	private Long id;
	/**
	 * 归还时间
	 */
	private Date revertTime;
	/**
	 * 归还数量
	 */
	private Integer count;
	/**
	 * 父记录
	 */
	private MaterialLendLog fatherLog;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getRevertTime() {
		return revertTime;
	}

	public void setRevertTime(Date revertTime) {
		this.revertTime = revertTime;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@JsonIgnore
	public MaterialLendLog getFatherLog() {
		return fatherLog;
	}

	public void setFatherLog(MaterialLendLog fatherLog) {
		this.fatherLog = fatherLog;
	}
}
