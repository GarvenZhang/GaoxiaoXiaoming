/**
 * @author zmj
 * 文件夹类
 */
package com.xiaoming.domain;

import java.util.Date;
import java.util.Set;

public class Folder {
	private Long id;
	private String name;
	private Date date = new Date();
	private Organization organization;
	private Boolean avariable = true;
	private Folder parent;
	private Set<Document> documents;
	private Set<Folder> childers;
	private Boolean isPublic;
	
	
	public Boolean getAvariable() {
		return avariable;
	}
	public void setAvariable(Boolean avariable) {
		this.avariable = avariable;
	}
	public Boolean getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}
	public Set<Folder> getChilders() {
		return childers;
	}
	public void setChilders(Set<Folder> childers) {
		this.childers = childers;
	}
	public Set<Document> getDocuments() {
		return documents;
	}
	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public boolean isAvariable() {
		return avariable;
	}
	public void setAvariable(boolean avariable) {
		this.avariable = avariable;
	}
	public Folder getParent() {
		return parent;
	}
	public void setParent(Folder parent) {
		this.parent = parent;
	}
	
}
