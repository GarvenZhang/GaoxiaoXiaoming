package com.xiaoming.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str){
		if(str==null || "".equals(str))
			return true;
		return false;
	}
	
	/**
	 * 判断是否为电话号码
	 * @param str
	 * @return
	 */
	public static boolean isPhoneNumber(String str){
		Pattern pattern = Pattern.compile("^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
}
