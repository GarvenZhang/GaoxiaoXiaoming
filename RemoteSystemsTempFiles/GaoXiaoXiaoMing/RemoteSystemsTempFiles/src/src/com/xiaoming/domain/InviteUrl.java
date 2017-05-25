package com.xiaoming.domain;

public class InviteUrl {
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 密匙
	 */
	private String key;
	/**
	 * 二维码存放路径
	 */
	private String path;
	/**
	 * 对应的组织
	 */
	private Organization organization;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
