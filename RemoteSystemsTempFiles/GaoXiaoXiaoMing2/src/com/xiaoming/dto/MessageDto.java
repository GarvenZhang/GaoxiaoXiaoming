package com.xiaoming.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.xiaoming.domain.Member;
import com.xiaoming.domain.Message;
import com.xiaoming.domain.UsersMessage;
import com.xiaoming.util.JsonIgnore;

public class MessageDto {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 请假内容
	 */
	private String content;
	/**
	 * 创建时间
	 */
	private Date publishTime;
	/**
	 * 创建者
	 */
	private String publisher;
	/**
	 * 接收者
	 */
	private ArrayList<UsersMessageDto> receivers;
	private String[] members;
	/**
	 * 用户阅读情况
	 */
	private Boolean read;
	
	public MessageDto(){
		
	}
	
	public MessageDto(Message message){
		id = message.getId().toString();
		content = message.getContent();
		publisher = message.getPublisher().getUser().getRealName();
		publishTime = message.getPublishTime();
		
		ArrayList<UsersMessageDto> dtoList = new ArrayList<>();
		for (UsersMessage usersMessage : message.getUsersMessage()) {
			UsersMessageDto dto = new UsersMessageDto();
			Member member = usersMessage.getReceiver();
			dto.setMemberId(member.getId().toString());
			dto.setName(member.getUser().getRealName());
			dto.setRead(usersMessage.getIsRead());
			
			dtoList.add(dto);
		}
		receivers = dtoList;
	}
	@JsonIgnore
	public String[] getMembers() {
		return members;
	}

	public void setMembers(String[] receiverId) {
		this.members = receiverId;
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

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public ArrayList<UsersMessageDto> getReceivers() {
		return receivers;
	}

	public void setReceivers(ArrayList<UsersMessageDto> receivers) {
		this.receivers = receivers;
	}
	
	@JsonIgnore
	public Boolean getRead() {
		return read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}
	
	//控制屬性
	/**
	 * 控制類型
	 */
	private int type;
	private int pageSize;
	private int pageNum;
	
	@JsonIgnore
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
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
	
	@JsonIgnore
	public Map<String,Class> getClassMap(){
		Map<String,Class> classMap = new HashMap<>();
		classMap.put("receivers", UsersMessageDto.class);
		return classMap;
	}
}
