package com.xiaoming.dto;

import com.xiaoming.domain.Image;
import com.xiaoming.domain.Organization;
import com.xiaoming.util.JsonIgnore;

public class OrganizationDto {
	/**
	 * 主键id
	 */
	private long id;
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
	private String phone;
	/**
	 * 办公地址
	 */
	private String officeAddress;
	/**
	 * 存放logo的路径
	 */
	private String logoUrl;
	private String logoId;
	/**
	 * 组织介绍
	 */
	private String orgDescription;
	/**
	 * 是否对同校组织公开联系方式
	 */
	private boolean isPublic;
	/**
	 * 组织所在校区
	 */
	private UniversityDto university;
	/**
	 * 校区
	 */
	private CampusDto campus;
	/**
	 * 所在小区的id
	 */
	private String campusId;
	/**
	 * 要修改的字段
	 */
	private String[] modify;

	private int pageSize;
	private int pageNum;
	
	@JsonIgnore
	public String getLogoId() {
		return logoId;
	}

	public void setLogoId(String logoId) {
		this.logoId = logoId;
	}

	public OrganizationDto() {

	}

	public OrganizationDto(Organization org){
		this.setContactName(org.getContactName());
		this.setPhone(org.getContactPhoneNumber());
		this.setId(org.getId());
		Image image = null;
		if((image = org.getLogo())!=null){ //如果还没上传logo
			this.setLogoUrl(image.getUrl());
		}else{
			this.setLogoUrl("");
		}
		this.setName(org.getName());
		this.setOfficeAddress(org.getOfficeAddress());
		this.setOrgDescription(org.getOrgDescription());
		Boolean _public = org.getIsPublic();
		if(_public != null){ //未设置，默认为不公开
			this.setPublic(org.getIsPublic());
		}else{
			this.setPublic(false);
		}
		this.setUniversity(new UniversityDto(org.getCampus()));
		this.setCampus(new CampusDto(org.getCampus()));
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
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
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * @param contactName
	 *            the contactName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the officeAddress
	 */
	public String getOfficeAddress() {
		return officeAddress;
	}

	/**
	 * @param officeAddress
	 *            the officeAddress to set
	 */
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	/**
	 * @return the logoUrl
	 */
	public String getLogoUrl() {
		return logoUrl;
	}

	/**
	 * @param logoUrl
	 *            the logoUrl to set
	 */
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	/**
	 * @return the orgDescription
	 */
	public String getOrgDescription() {
		return orgDescription;
	}

	/**
	 * @param orgDescription
	 *            the orgDescription to set
	 */
	public void setOrgDescription(String orgDescription) {
		this.orgDescription = orgDescription;
	}

	/**
	 * @return the isPublic
	 */
	public boolean isPublic() {
		return isPublic;
	}

	/**
	 * @param isPublic
	 *            the isPublic to set
	 */
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	/**
	 * @return the university
	 */
	public UniversityDto getUniversity() {
		return university;
	}

	/**
	 * @param university
	 *            the university to set
	 */
	public void setUniversity(UniversityDto university) {
		this.university = university;
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
	public String getCampusId() {
		return campusId;
	}

	public void setCampusId(String campusId) {
		this.campusId = campusId;
	}

	public CampusDto getCampus() {
		return campus;
	}

	public void setCampus(CampusDto campus) {
		this.campus = campus;
	}
	
	@JsonIgnore
	public String[] getModify() {
		return modify;
	}

	public void setModify(String[] modify) {
		this.modify = modify;
	}
	
}
