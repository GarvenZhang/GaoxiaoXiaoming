package com.xiaoming.domain;

import java.util.Set;


/**
 * 学生组织（社团）类
 * 
 * @author Yunfei
 *
 */
public class Organization {
	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 组织名称
	 */
	private String name;
	/**
	 * 对外联系人，姓名
	 */
	private String contactName;
	/**
	 * 对外联系人，电话号码
	 */
	private String contactPhoneNumber;
	/**
	 * 办公地址
	 */
	private String officeAddress;
	/**
	 * 存放logo的路径
	 */
	private Image logo;
	/**
	 * 组织介绍
	 */
	private String orgDescription;
	/**
	 * 是否对同校组织公开联系方式
	 */
	private Boolean isPublic;
	/**
	 * 当前届
	 */
	private String currentEdition;
	
	/*
	 * 一对一映射
	 */
	
	/**
	 * 组织所在学校
	 */
	private Campus campus;
	
	/*
	 * 一对多映射 
	 */
	
	/**
	 * 部门集合
	 */
	private Set<Department> departments;
	/**
	 * 项目集合
	 *   一个社团可以添加多个项目
	 */
	private Set<Project> projects;
	/**
	 * 物资集合
	 */
	private Set<Material> materials;
	/**
	 * 文档列表
	 */
	private Set<Document> documents;
	
	/*
	 * 方法区
	 */
	/**
	 * @return 主键，id
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
	 * @return the 组织名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name 组织名称
	 *            the 组织名称 to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the 对外联系人姓名
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * @param contactName 对外联系人姓名
	 *            the 对外联系人姓名 to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * @return the 对外联系方式
	 */
	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}

	/**
	 * @param contactPhoneNumber 对外联系方式
	 *            the 对外联系方式 to set
	 */
	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}

	/**
	 * @return the 办公地址
	 */
	public String getOfficeAddress() {
		return officeAddress;
	}

	/**
	 * @param officeAddress 办公地址
	 *            the 办公地址 to set
	 */
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	/**
	 * @return the logo
	 */
	public Image getLogo() {
		return logo;
	}

	/**
	 * @param logo the logo to set
	 */
	public void setLogo(Image logo) {
		this.logo = logo;
	}

	/**
	 * @return the 组织描述
	 */
	public String getOrgDescription() {
		return orgDescription;
	}

	/**
	 * @param orgDescription 组织描述
	 *            the 组织描述 to set
	 */
	public void setOrgDescription(String orgDescription) {
		this.orgDescription = orgDescription;
	}

	/**
	 * @return the 是否对同校组织公开联系方式
	 */
	public Boolean getIsPublic() {
		return isPublic;
	}

	/**
	 * @param isPublic 是否对同校组织公开联系方式
	 *            the 是否对同校组织公开联系方式 to set
	 */
	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	/**
	 * @return the 所在校区
	 */
	public Campus getCampus() {
		return campus;
	}

	/**
	 * @param campus 所在校区 the 所在校区 to set
	 */
	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	/**
	 * @return the 拥有的部门
	 */
	public Set<Department> getDepartments() {
		return departments;
	}

	/**
	 * @param departments 拥有的部门集合 the departments to set
	 */
	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

	/**
	 * @return the 拥有的项目集合
	 */
	public Set<Project> getProjects() {
		return projects;
	}

	/**
	 * @param projects 项目集合 the projects to set
	 */
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	/**
	 * @return the 物资集合
	 */
	public Set<Material> getMaterials() {
		return materials;
	}

	/**
	 * @param materials 物资集合 the materials to set
	 */
	public void setMaterials(Set<Material> materials) {
		this.materials = materials;
	}

	/**
	 * @return the documents
	 */
	public Set<Document> getDocuments() {
		return documents;
	}

	/**
	 * @param documents the documents to set
	 */
	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

	public String getCurrentEdition() {
		return currentEdition;
	}

	public void setCurrentEdition(String currentEdition) {
		this.currentEdition = currentEdition;
	}
	
}
