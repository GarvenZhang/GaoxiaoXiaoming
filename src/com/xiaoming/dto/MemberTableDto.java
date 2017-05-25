package com.xiaoming.dto;

import com.xiaoming.domain.Member;

/**
 * 通讯录
 * @author Yunfei
 *
 */
public class MemberTableDto {
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 部门
	 */
	private String department;
	/**
	 * 职位
	 */
	private String position;
	/**
	 * 电话
	 */
	private String phone;
	
	public MemberTableDto(){
		
	}
	
	public MemberTableDto(Member m){
		name = m.getUser().getRealName();
		department = m.getDepartment().getName();
		position = m.getPosition();
		phone = m.getUser().getPhoneNumber();
	}

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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
