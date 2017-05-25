package com.xiaoming.domain;

import java.util.Date;

/**
 * 用户注册
 * 		用于记录短信验证码
 * @author Yunfei
 *
 */
public class UserRegister {
	/**
	 * 主键，id
	 */
	private Long id;
	/**
	 * 验证码
	 */
	private String captcha;
	/**
	 * 是否有效
	 */
	private Boolean isValid;
	/**
	 * 验证码生成时间
	 */
	private Date createTime;
	/**
	 * 对应用户
	 */
	private User user;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the captcha
	 */
	public String getCaptcha() {
		return captcha;
	}
	/**
	 * @param captcha the captcha to set
	 */
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	/**
	 * @return the isValid
	 */
	public Boolean getIsValid() {
		return isValid;
	}
	/**
	 * @param isValid the isValid to set
	 */
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
