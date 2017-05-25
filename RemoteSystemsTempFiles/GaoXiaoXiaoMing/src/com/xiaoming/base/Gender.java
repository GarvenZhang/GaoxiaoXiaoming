package com.xiaoming.base;

/**
 * 性别
 * @author Yunfei
 *
 */
public enum Gender {
	FEMALE("女"),MALE("男"),UNKNOWN("未知");
	
	private String name;
	
	Gender(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name();
	}
}
