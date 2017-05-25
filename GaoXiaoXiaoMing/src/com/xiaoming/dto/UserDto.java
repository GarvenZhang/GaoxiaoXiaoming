package com.xiaoming.dto;

import com.xiaoming.domain.User;
import com.xiaoming.util.JsonIgnore;

public class UserDto extends User {
	/**
	 * 验证码
	 */
	private String code;
	/**
	 * 新密码
	 */
	private String newPassword;
	/**
	 * 校区的id
	 */
	private String campusId;
	/**
	 * 组织的名称，（注册组织的时候用）
	 */
	private String orgName;

	public UserDto(User user) {
		
	}

	public UserDto() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword
	 *            the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	@JsonIgnore
	public String getCampusId() {
		return campusId;
	}

	public void setCampusId(String campusId) {
		this.campusId = campusId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
