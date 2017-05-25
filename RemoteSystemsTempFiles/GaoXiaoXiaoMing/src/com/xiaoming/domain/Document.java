package com.xiaoming.domain;

import java.util.Date;

/**
 * 文档类
 * @author Yunfei
 *
 */
public class Document {
	/**
	 * 主键，id
	 */
	private Long id;
	/**
	 * 文档名
	 */
	private String name;
	/**
	 * 文件类型
	 */
	private String fileType;
	/**
	 * 存放url
	 */
	private String url;
	/**
	 * 上传者
	 */
	private User uploader;
	/**
	 * 所属组织
	 */
	private Organization organization;
	/**
	 * 上传时间
	 */
	private Date uploadTime;
	/**
	 * 是否公开
	 */
	private Boolean isPublic;
	/**
	 * 对应的文件夹
	 */
	private Folder folder;
	
	public Folder getFolder() {
		return folder;
	}
	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	/*
	 * Getter and Setter
	 */
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
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}
	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the uploader
	 */
	public User getUploader() {
		return uploader;
	}
	/**
	 * @param uploader the uploader to set
	 */
	public void setUploader(User uploader) {
		this.uploader = uploader;
	}
	/**
	 * @return the organization
	 */
	public Organization getOrganization() {
		return organization;
	}
	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	/**
	 * @return the uploadTime
	 */
	public Date getUploadTime() {
		return uploadTime;
	}
	/**
	 * @param uploadTime the uploadTime to set
	 */
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	/**
	 * @return the isPublic
	 */
	public Boolean getIsPublic() {
		return isPublic;
	}
	/**
	 * @param isPublic the isPublic to set
	 */
	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}	
}
