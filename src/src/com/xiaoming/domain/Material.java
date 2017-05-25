package com.xiaoming.domain;

import java.util.Date;
import java.util.Set;

/**
 * 物资类
 * 
 * @author Yunfei
 *
 */
public class Material {
	/**
	 * 主键，id
	 */
	private Long id;
	/**
	 * 物资名称
	 */
	private String name;
	/**
	 * 拥有的总量
	 */
	private Integer totalCount;
	/**
	 * 借出数量
	 */
	private Integer lentCount;
	/**
	 * 现存数量
	 */
	private Integer existCount;
	/**
	 * 存放位置
	 */
	private String storageLocation;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 是否删除了
	 */
	private Boolean isDeleted;

	/*
	 * 一对一映射
	 */
	/**
	 * 该物资所属的社团
	 */
	private Organization orgBelongTo;

	/*
	 * 一对多映射
	 */
	/**
	 * 该物资的出借记录
	 */
	private Set<MaterialLendLog> lendLog;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the totalCount
	 */
	public Integer getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount
	 *            the totalCount to set
	 */
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the lentCount
	 */
	public Integer getLentCount() {
		return lentCount;
	}

	/**
	 * @param lentCount
	 *            the lentCount to set
	 */
	public void setLentCount(Integer lentCount) {
		this.lentCount = lentCount;
	}

	/**
	 * @return the existCount
	 */
	public Integer getExistCount() {
		return existCount;
	}

	/**
	 * @param existCount
	 *            the existCount to set
	 */
	public void setExistCount(Integer existCount) {
		this.existCount = existCount;
	}

	/**
	 * @return the storageLocation
	 */
	public String getStorageLocation() {
		return storageLocation;
	}

	/**
	 * @param storageLocation
	 *            the storageLocation to set
	 */
	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}

	/**
	 * @return the orgBelongTo
	 */
	public Organization getOrgBelongTo() {
		return orgBelongTo;
	}

	/**
	 * @param orgBelongTo
	 *            the orgBelongTo to set
	 */
	public void setOrgBelongTo(Organization orgBelongTo) {
		this.orgBelongTo = orgBelongTo;
	}

	/**
	 * @return the lendLog
	 */
	public Set<MaterialLendLog> getLendLog() {
		return lendLog;
	}

	/**
	 * @param lendLog
	 *            the lendLog to set
	 */
	public void setLendLog(Set<MaterialLendLog> lendLog) {
		this.lendLog = lendLog;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
