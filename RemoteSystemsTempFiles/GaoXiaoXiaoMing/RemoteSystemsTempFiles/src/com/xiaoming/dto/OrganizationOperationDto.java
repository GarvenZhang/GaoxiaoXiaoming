package com.xiaoming.dto;

import java.util.Date;

import com.xiaoming.domain.OrganizationOperation;

public class OrganizationOperationDto {
	private String id;
	private String description;
	private Date time;
	
	public OrganizationOperationDto(){
		
	}
	
	public OrganizationOperationDto(OrganizationOperation oo){
		id = oo.getId().toString();
		description = oo.getDescription();
		time = oo.getTime();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
