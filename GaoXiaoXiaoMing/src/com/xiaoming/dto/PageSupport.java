package com.xiaoming.dto;

import com.xiaoming.util.JsonIgnore;

public class PageSupport {
	/**
	 * 页面大小
	 */
	private int pageSize;
	/**
	 * 分页的起始页
	 */
	private int pageNum;
	/**
	 * 排序字段
	 */
	private String sort;
	/**
	 * 排序方式
	 */
	private String order;

	/**
	 * @return the sort
	 */
	@JsonIgnore
	public String getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * @return the order
	 */
	@JsonIgnore
	public String getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * @return the pageSize
	 */
	@JsonIgnore
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the pageNum
	 */
	@JsonIgnore
	public int getPageNum() {
		return pageNum;
	}

	/**
	 * @param pageNum the pageNum to set
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}



}
