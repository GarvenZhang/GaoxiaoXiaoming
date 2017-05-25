package com.xiaoming.domain;

import java.util.Date;
import java.util.Set;

import com.xiaoming.base.Gender;
import com.xiaoming.base.Role;
import com.xiaoming.constants.Constants;

/**
 * 基础用户类，由其他用户类继承
 * 
 * @author Yunfei
 *
 */
public class User {
	public User(User u){
		this.realName = u.getRealName();
	}
	/**
	 * 主键，id
	 */
	private Long id;
	/**
	 * 账号，登陆用
	 */
	private String loginName;
	/**
	 * 密码
	 */
	private String password;
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
	private Role role;
	/**
	 * 所在组织 仅组织管理员用户填写
	 */
	private Organization organization;
	/**
	 * 是否有效
	 */
	private Byte state = 0;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 性别
	 */
	private Gender gender;
	/**
	 * 专业
	 */
	private Major major;
	/**
	 * 所在校区
	 */
	private Campus campus;
	/**
	 * 年级
	 */
	private Grade grade;
	/**
	 * 联系方式
	 */
	private String phoneNumber;
	/**
	 * 头像Url
	 */
	private Image logo;
	/**
	 * 默认打开的社团
	 */
	private Member defaultMember;

	/*
	 * 映射
	 */
	/**
	 * 反馈信息
	 */
	private Set<FeedBack> feedbacks;
	/**
	 * 用户扮演的成员
	 */
	private Set<Member> members;

	/*
	 * 构造器
	 */
	/**
	 * 空构造器
	 */
	public User() {
		super();
	}

	/*
	 * Getter and Setter
	 */
	/**
	 * @return the defaultMember
	 */
	public Member getDefaultMember() {
		return defaultMember;
	}

	/**
	 * @param defaultMember
	 *            the defaultMember to set
	 */
	public void setDefaultMember(Member defaultMember) {
		this.defaultMember = defaultMember;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @param realName
	 *            the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	// /**
	// * @return the clazz
	// */
	// public Clazz getClazz() {
	// return clazz;
	// }
	//
	// /**
	// * @param clazz the clazz to set
	// */
	// public void setClazz(Clazz clazz) {
	// this.clazz = clazz;
	// }

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @return the campus
	 */
	public Campus getCampus() {
		return campus;
	}

	/**
	 * @param campus
	 *            the campus to set
	 */
	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	/**
	 * @return the grade
	 */
	public Grade getGrade() {
		return grade;
	}

	/**
	 * @param grade
	 *            the grade to set
	 */
	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the logo
	 */
	public Image getLogo() {
		return logo;
	}

	/**
	 * @param logo
	 *            the logo to set
	 */
	public void setLogo(Image logo) {
		this.logo = logo;
	}

	/**
	 * @param organization
	 *            the organization to set
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/**
	 * @return the organization
	 */
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the loginName
	 */
	public String getloginName() {
		return loginName;
	}

	/**
	 * @param loginName
	 *            the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the registerDate
	 */
	public Date getRegisterDate() {
		return registerDate;
	}

	/**
	 * @param registerDate
	 *            the registerDate to set
	 */
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	/**
	 * @return the lastLoginTime
	 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * @param lastLoginTime
	 *            the lastLoginTime to set
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * @return the lastLoginIP
	 */
	public String getLastLoginIP() {
		return lastLoginIP;
	}

	/**
	 * @param lastLoginIP
	 *            the lastLoginIP to set
	 */
	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	/**
	 * @return the feedbacks
	 */
	public Set<FeedBack> getFeedbacks() {
		return feedbacks;
	}

	/**
	 * @param feedbacks
	 *            the feedbacks to set
	 */
	public void setFeedbacks(Set<FeedBack> feedbacks) {
		this.feedbacks = feedbacks;
	}

	/**
	 * @return the members
	 */
	public Set<Member> getMembers() {
		return members;
	}

	/**
	 * @param members
	 *            the members to set
	 */
	public void setMembers(Set<Member> members) {
		this.members = members;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public boolean isValid() {
		return this.state == Constants.USER_NORMAL ? true : false;
	}
}
