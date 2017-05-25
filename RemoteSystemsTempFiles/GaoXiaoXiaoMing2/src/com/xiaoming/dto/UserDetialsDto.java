package com.xiaoming.dto;

import java.util.Date;

import com.xiaoming.base.Gender;
import com.xiaoming.base.Role;
import com.xiaoming.domain.Campus;
import com.xiaoming.domain.Grade;
import com.xiaoming.domain.Image;
import com.xiaoming.domain.Major;
import com.xiaoming.domain.Member;
import com.xiaoming.domain.Organization;
import com.xiaoming.domain.User;

public class UserDetialsDto {
	/**
	 * 主键，id
	 */
	private String id;
	/**
	 * 账号，登陆用
	 */
	private String loginName;
	/**
	 * 注册时间
	 */
	private Date registerDate;
	/**
	 * 最后登录的时间
	 */
	private Date lastLoginTime;
	/**
	 * 最后登录的ip地址
	 */
	private String lastLoginIP;
	/**
	 * 权限，有三种： 1.组织成员用户 2。组织管理员用户 3.系统管理员用户
	 */
	private String role;
	/**
	 * 所在组织 仅组织管理员用户填写
	 */
	private String organization;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 专业
	 */
	private String major;
	/**
	 * 所在校区
	 */
	private String campus;
	/**
	 * 所在学校
	 */
	private String university;
	/**
	 * 年级
	 */
	private String grade;
	/**
	 * 联系方式
	 */
	private String phoneNumber;
	/**
	 * 头像Url
	 */
	private String logoUrl;
	/**
	 * 默认打开的社团
	 */
	private String defaultOrg;

	public UserDetialsDto() {

	}

	public UserDetialsDto(User user) {
		if (user.getRole().equals(Role.ORG_USER)) {
			campus = user.getCampus()==null?"":user.getCampus().getName();
			if (user.getDefaultMember() == null) {
				defaultOrg = "";
			} else {
				defaultOrg = user.getDefaultMember().getDepartment().getOrgBelong().getName();
			}
			if (user.getGender() != null)
				gender = user.getGender().getName();
			if (user.getMajor() != null)
				major = user.getMajor().getName();
			if (user.getLogo() != null)
				logoUrl = user.getLogo().getUrl();
			if (user.getCampus() != null)
				university = user.getCampus().getUniversity().getName();
			Organization org = user.getOrganization();
			if (org != null) {
				organization = org.getName();
			}
		}
		if (user.getGrade() != null)
			grade = user.getGrade().getName();
		id = user.getId().toString();
		lastLoginIP = user.getLastLoginIP();
		lastLoginTime = user.getLastLoginTime();
		loginName = user.getLoginName();
		phoneNumber = user.getPhoneNumber();
		realName = user.getRealName();
		registerDate = user.getRegisterDate();
		role = user.getRole().getName();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getDefaultOrg() {
		return defaultOrg;
	}

	public void setDefaultOrg(String defaultOrg) {
		this.defaultOrg = defaultOrg;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

}
