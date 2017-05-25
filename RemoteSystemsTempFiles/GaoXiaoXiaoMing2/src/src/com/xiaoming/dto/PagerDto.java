package com.xiaoming.dto;

import com.xiaoming.domain.Pager;

/**
 * 用来接收json
 * @author Yunfei
 *
 * @param <T>
 */
public class PagerDto<T> extends Pager<T> {
	/**
	 * id
	 */
	private long id;
	/**
	 * 类型
	 */
	private String type;
	
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
	//	/**
//	 * @return the id
//	 */
//	public Long getId() {
//		return id;
//	}
//	/**
//	 * @param id the id to set
//	 */
//	public void setId(Long id) {
//		this.id = id;
//	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
}
