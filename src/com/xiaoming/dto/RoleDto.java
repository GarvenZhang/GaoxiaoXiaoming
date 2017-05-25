package com.xiaoming.dto;

public class RoleDto {
	private String role;
	
	public RoleDto(){
		
	}

	/**
	 * @param role
	 */
	public RoleDto(String role) {
		super();
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
