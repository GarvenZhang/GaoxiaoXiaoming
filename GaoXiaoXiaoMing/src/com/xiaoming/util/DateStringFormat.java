/**
 * @author JJ
 * 2016/4/15
 * 字符串与日期的相互转换
 */
package com.xiaoming.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStringFormat {
	//字符串转换成日期
	public static String DateFormatString(Date date,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);  
		return sdf.format(date); 
	}
	
	//日期转换成字符串
	public static Date StringFormatDate(String date,String format) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.parse(date);  
	}
}
