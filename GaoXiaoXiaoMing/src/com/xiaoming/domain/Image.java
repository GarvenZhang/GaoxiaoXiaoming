package com.xiaoming.domain;

import java.util.Date;

/**
 * 图片类
 * 
 * @author Yunfei
 *
 */
public class Image {
	/**
	 * 主键，id
	 */
	private Long id;
	/**
	 * 图片url
	 */
	private String url;
	/**
	 * 图片名称
	 */
	private String imageName;
	/**
	 * 上传时间
	 */
	private Date uploadTime;
	/**
	 * 上传者
	 */
	private User uploader;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * @param imageName
	 *            the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/**
	 * @return the uploadTime
	 */
	public Date getUploadTime() {
		return uploadTime;
	}

	/**
	 * @param uploadTime
	 *            the uploadTime to set
	 */
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	/**
	 * @return the uploader
	 */
	public User getUploader() {
		return uploader;
	}

	/**
	 * @param uploader
	 *            the uploader to set
	 */
	public void setUploader(User uploader) {
		this.uploader = uploader;
	}
}
