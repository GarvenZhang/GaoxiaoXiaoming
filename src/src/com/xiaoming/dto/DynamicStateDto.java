package com.xiaoming.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.xiaoming.domain.DynamicState;
import com.xiaoming.util.JsonIgnore;

/**
 * 工作动态
 * @author Yunfei
 *
 */
public class DynamicStateDto {
	private String id;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 操作者
	 */
	private String operator;
	/**
	 * 工作
	 */
	private AssignmentDto assignment;
	/**
	 * 项目的id
	 */
	private String projectId;
	/**
	 * 发生的时间
	 */
	private Date time;
	
	private int pageSize;
	private int pageNum;
	
	public DynamicStateDto(){
		
	}
	
	public DynamicStateDto(DynamicState ds){
		id = ds.getId().toString();
		description = ds.getDescription();
		assignment = new AssignmentDto(ds.getAssignment());
		operator = ds.getMember().getUser().getRealName();
		time = ds.getOperaTime();
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public AssignmentDto getAssignment() {
		return assignment;
	}
	public void setAssignment(AssignmentDto assignment) {
		this.assignment = assignment;
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
	
	public static Map<String,Class> getClassMap(){
		Map<String,Class> classMap = new HashMap<>();
		classMap.put("assignment", AssignmentDto.class);
		return classMap;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	
}
