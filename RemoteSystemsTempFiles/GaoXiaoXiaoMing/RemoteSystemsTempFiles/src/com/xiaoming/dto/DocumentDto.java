package com.xiaoming.dto;

import java.util.Date;

import com.xiaoming.domain.Document;

public class DocumentDto {
	private Long id;
	private String name;
	private String fileType;
	private String url;
	private UserDto user;
	private OrganizationDto organization;
	private Date uploadTime;
	private Boolean isPublic;
	private Long floderId;
	
	
	public Long getFloderId() {
		return floderId;
	}
	public void setFloderId(Long floderId) {
		this.floderId = floderId;
	}
	public DocumentDto(){}
	
	public DocumentDto(Document d){
		this.id = d.getId();
		this.name = d.getName();
		this.fileType = d.getFileType();
		this.url = d.getUrl();
		this.user = new UserDto(d.getUploader());
		this.organization = new OrganizationDto(d.getOrganization());
		this.uploadTime = d.getUploadTime();
		this.isPublic = d.getIsPublic();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public OrganizationDto getOrganization() {
		return organization;
	}
	public void setOrganization(OrganizationDto organization) {
		this.organization = organization;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public Boolean getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}
	
}
