package com.xiaoming.dto;

import com.xiaoming.domain.Project;
import com.xiaoming.util.JsonIgnore;

/**
 * Project 的数据传输对象
 * @author Yunfei
 *
 */
public class ProjectDto {
	
	private long id;
	private String name;
	private boolean isNew;
	
	@Override
	public String toString(){
		return "id:"+id+"|name:"+name+"|isNew:"+isNew;
	}
	
	public ProjectDto(){
		
	}
	
	/**
	 * @param id
	 * @param name
	 */
	public ProjectDto(Project project) {
		this.id = project.getId();
		this.name = project.getName();
	}

	/**
	 * @return the isNew
	 */
	@JsonIgnore
	public boolean isNew() {
		return isNew;
	}

	/**
	 * @param isNew the isNew to set
	 */
	public void setIsNew(boolean isNew) {
		this.isNew = isNew;
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
}
