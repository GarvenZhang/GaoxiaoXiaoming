package com.xiaoming.dto;

/**
 * Json支持类，规范Json格式
 * @author Yunfei
 *
 */
public interface JsonSupport {
	/**
	 * 设置状态
	 * true：成功
	 * false：失败
	 */
	public void setStatus(boolean status);
	/**
	 * 设置错误信息
	 * @param message
	 */
	public void setErrorMessage(String message);
}
