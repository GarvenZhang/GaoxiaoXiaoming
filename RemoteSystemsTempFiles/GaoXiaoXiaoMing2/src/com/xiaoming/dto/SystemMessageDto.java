package com.xiaoming.dto;

import java.util.Date;

import com.xiaoming.domain.SystemMessage;
import com.xiaoming.util.JsonIgnore;

public class SystemMessageDto{
	private String id;
	private String content;
	private Date time;
	
	private int pageSize;
	private int pageNum;
	
	public SystemMessageDto() {
		// TODO Auto-generated constructor stub
	}
	
	public SystemMessageDto(SystemMessage sysm){
		id = sysm.getId().toString();
		content = sysm.getContent();
		time = sysm.getPublishTime();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
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
	
	
}