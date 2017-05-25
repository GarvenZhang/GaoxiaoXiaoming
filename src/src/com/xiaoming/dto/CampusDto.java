package com.xiaoming.dto;

import com.xiaoming.domain.Campus;

public class CampusDto {
	private String id;
	private String name;
	
	public CampusDto(){
		
	}
	
	public CampusDto(Campus campus){
		this.setId(campus.getId().toString());
		this.setName(campus.getName());
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
