package com.xiaoming.dto;

import java.util.Date;

import com.xiaoming.domain.MaterialLendLogChild;

/**
 * 出借记录的子记录
 * @author Yunfei
 *
 */
public class MaterialLendLogChildDto {
	private String id;
	private Date time;
	private int count;
	
	public MaterialLendLogChildDto(){
		
	}
	
	public MaterialLendLogChildDto(MaterialLendLogChild child){
		id = child.getId().toString();
		time = child.getRevertTime();
		count = child.getCount();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
