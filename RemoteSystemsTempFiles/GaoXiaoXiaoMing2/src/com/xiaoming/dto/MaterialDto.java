package com.xiaoming.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.xiaoming.constants.Constants;
import com.xiaoming.domain.Material;
import com.xiaoming.domain.MaterialLendLog;
import com.xiaoming.util.JsonIgnore;

public class MaterialDto {
	/**
	 * 主键，id
	 */
	private String id;
	/**
	 * 物资名称
	 */
	private String name;
	/**
	 * 拥有的总量
	 */
	private int totalCount;
	/**
	 * 借出数量
	 */
	private int lentCount;
	/**
	 * 状态
	 */
	private int state;
	/**
	 * 存放位置
	 */
	private String storageLocation;
	
	private int pageSize;
	private int pageNum;
	
	private ArrayList<MaterialLendLogDto> lendLogs;
	private ArrayList<MaterialDto> materials;
	
	public MaterialDto(){
		
	}
	
	public MaterialDto(Material material){
		id = material.getId().toString();
		name = material.getName();
		totalCount = material.getTotalCount();
		lentCount = material.getLentCount();
		storageLocation = material.getStorageLocation();
		
		//统计出借情况
		if(lentCount > 0){ //有借出
			for (MaterialLendLog lentLog : material.getLendLog()) {
				Date revert = lentLog.getRevertDate();
				if(revert.getTime() < new Date().getTime()){ //逾期
					state = Constants.MATERIAL_OUT_OF_DATE;
				}
			}
			//未逾期
			state = state==Constants.MATERIAL_OUT_OF_DATE?state:Constants.MATERIAL_LENT;
		}else{
			state = Constants.MATERIAL_NORMAL;
		}
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

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getLentCount() {
		return lentCount;
	}

	public void setLentCount(int lentCount) {
		this.lentCount = lentCount;
	}

	public String getStorageLocation() {
		return storageLocation;
	}

	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}

	public ArrayList<MaterialLendLogDto> getLendLogs() {
		return lendLogs;
	}

	public void setLendLogs(ArrayList<MaterialLendLogDto> lendLogs) {
		this.lendLogs = lendLogs;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	/*
	 * =========转换时要忽略的字段==========
	 */
	
	/**
	 * 借方联系方式
	 */
	private String phone;
	/**
	 * 借出日期
	 */
	private Date borrowDate;
	/**
	 * 归还日期
	 */
	private Date revertDate;
	
	private int count;

	@JsonIgnore
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@JsonIgnore
	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	@JsonIgnore
	public Date getRevertDate() {
		return revertDate;
	}

	public void setRevertDate(Date revertDate) {
		this.revertDate = revertDate;
	}
	@JsonIgnore
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	@JsonIgnore
	public ArrayList<MaterialDto> getMaterials() {
		return materials;
	}

	public void setMaterials(ArrayList<MaterialDto> materials) {
		this.materials = materials;
	}
	
	public static Map<String, Class> getClassMap(){
		Map<String, Class> classMap = new HashMap<>();
		classMap.put("materials", MaterialDto.class);
		return classMap;
	}
	
}
