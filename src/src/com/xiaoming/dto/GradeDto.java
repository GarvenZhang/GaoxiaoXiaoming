package com.xiaoming.dto;

import com.xiaoming.domain.Grade;

/**
 * 年级
 * @author Yunfei
 *
 */
public class GradeDto {
	private String id;
	private String name;
	
	public GradeDto(){
		
	}
	
	public GradeDto(Grade grade){
		id = grade.getId().toString();
		name = grade.getName();
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

}
