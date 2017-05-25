/**
 * @author JJ
 * 2016/4/15
 * 生成随机字符串
 */
package com.xiaoming.util;

import java.util.Date;

public class RandomString {
	public static String createRandoString(){
		return DateStringFormat.DateFormatString(new Date(), "yyyy-MM-dd")+(int)(Math.random()*300000);
	}
}
