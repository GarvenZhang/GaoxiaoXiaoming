package com.xiaoming.dto;

import com.xiaoming.domain.Department;

public class DepartmentDto {
	private String id;
	private String name;
	
	public DepartmentDto(){
		
	}
	
	public DepartmentDto(Department department){
		this.id = department.getId().toString();
		this.name = department.getName();
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
