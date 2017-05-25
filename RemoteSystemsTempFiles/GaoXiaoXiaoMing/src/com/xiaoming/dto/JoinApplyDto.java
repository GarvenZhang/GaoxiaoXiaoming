package com.xiaoming.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.xiaoming.domain.OrgJoinApplication;
import com.xiaoming.util.JsonIgnore;

/**
 * 请假申请
 * @author Yunfei
 *
 */
public class JoinApplyDto {
	/**
	 * id
	 */
	private String id;
	/**
	 * 对应的成员信息
	 */
	private UserInfoDto userInfo;
	/**
	 * 是否同意
	 */
	private boolean agree;
	/**
	 * 是否已处理
	 */
	private boolean handle;
	/**
	 * 申请的部门
	 */
	private String department;
	/**
	 * 处理的时间
	 */
	private Date handleTime;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 职位
	 */
	private String position;
	/**
	 * 原因
	 */
	private String reason;
	
	private int pageSize;
	private int pageNum;
	private int type;
	
	public JoinApplyDto(){
		
	}
	
	public JoinApplyDto(OrgJoinApplication apply){
		id = apply.getId().toString();
		department = apply.getDepartment().getName();
		position = apply.getPosition();
		name = apply.getUser().getRealName();
		handle = apply.getIsHandled();
		handleTime = apply.getHandleTime();
		reason = apply.getReason();
		//是否同意
		Boolean agree = apply.getIsPassed();
		if(agree != null){
			this.agree = agree;
		}else{ //默认不同意
			this.agree = false;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@JsonIgnore
	public UserInfoDto getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoDto userInfo) {
		this.userInfo = userInfo;
	}

	public boolean getAgree() {
		return agree;
	}

	public void setAgree(boolean agree) {
		this.agree = agree;
	}
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@JsonIgnore
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@JsonIgnore
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	

	public boolean isHandle() {
		return handle;
	}

	public void setHandle(boolean handle) {
		this.handle = handle;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}
	@JsonIgnore
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public static Map<String,Class> getClassMap(){
		Map<String,Class> classMap = new HashMap<>();
		classMap.put("userInfo", UserInfoDto.class);
		return classMap;
	}
}
