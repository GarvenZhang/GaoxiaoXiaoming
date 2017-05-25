package com.xiaoming.dto;

import com.google.gson.Gson;

/**
 * 返回結果
 * @author Yunfei
 *
 */
public class ResultDto {
	/**
	 * 狀態
	 * true：成功
	 * false：失敗
	 */
	private Boolean status;
	/**
	 * 錯誤信息（失敗時）
	 */
	private String errorMessage;
	/**
	 * 該用戶的角色（登陸時用到）
	 */
	private String role;
	
	
	/**
	 * 
	 */
	public ResultDto() {
		super();
	}

	/**
	 * @param errorMessage
	 */
	public ResultDto(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	/**
	 * @param status
	 * @param errorMessage
	 */
	public ResultDto(Boolean status, String errorMessage) {
		super();
		this.status = status;
		this.errorMessage = errorMessage;
	}

	/**
	 * @param status
	 * @param errorMessage
	 * @param role
	 */
	public ResultDto(Boolean status, String errorMessage, String role) {
		super();
		this.status = status;
		this.errorMessage = errorMessage;
		this.role = role;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	/**
	 * 成功
	 */
	public void success(){
		this.status = true;
	}
	
	/**
	 * 失敗
	 * @param message
	 */
	public void fail(String message){
		this.status=false;
		this.errorMessage = message;
	}
	
	public String toString(){
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
