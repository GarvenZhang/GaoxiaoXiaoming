package com.xiaoming.dto;

import java.util.Date;

import com.xiaoming.domain.AbsenceApply;

public class AbsenceApplyDto {
	/**
	 * id
	 */
	private long id;
	/**
	 * 真实姓名
	 */
	private String name;
	/**
	 * 是否同意
	 */
	private boolean isAgree;
	/**
	 * 是否处理
	 */
	private boolean isHandle;
	/**
	 * 处理时间
	 */
	private Date handleTime;
	
	public AbsenceApplyDto(){
		
	}
	
	public AbsenceApplyDto(AbsenceApply apply){
		//判断空值，Boolean转boolean遇到空值会抛异常
		if(apply.getIsAgree() == null){
			this.setIsAgree(false);
		}else{
			this.setIsAgree(apply.getIsAgree());
		}
		if(apply.getIsHandled()!=null){
			this.setIsHandle(apply.getIsHandled());
		}else{
			this.setIsHandle(false);
		}
		this.setHandleTime(apply.getHandleTime());
		this.setId(apply.getId());
		this.setName(apply.getAbsence().getMember().getUser().getRealName());
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
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

	/**
	 * @return the isAgree
	 */
	public boolean isAgree() {
		return isAgree;
	}
	/**
	 * @param isAgree the isAgree to set
	 */
	public void setIsAgree(boolean isAgree) {
		this.isAgree = isAgree;
	}
	/**
	 * @return the isHandle
	 */
	public boolean isHandle() {
		return isHandle;
	}
	/**
	 * @param isHandle the isHandle to set
	 */
	public void setIsHandle(boolean isHandle) {
		this.isHandle = isHandle;
	}
	/**
	 * @return the handleTime
	 */
	public Date getHandleTime() {
		return handleTime;
	}
	/**
	 * @param handleTime the handleTime to set
	 */
	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}
	
	
}
