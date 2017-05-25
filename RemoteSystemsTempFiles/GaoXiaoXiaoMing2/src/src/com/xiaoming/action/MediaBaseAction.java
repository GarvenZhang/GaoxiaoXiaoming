package com.xiaoming.action;

import java.io.File;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

public class MediaBaseAction<T> extends BaseAction<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String title;
	protected File file;
	protected String fileContentType;
	protected String fileFileName;
	protected String savePath;
	// 导出的excel文件的名称
	protected String fileName;
	protected String uploadFileName;
	// 用于到处excel表的输入流pload2;
	protected InputStream stream;
	
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getSavePath() {
		return this.savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public InputStream getStream() {
		return stream;
	}
	public void setStream(InputStream stream) {
		this.stream = stream;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	
}
