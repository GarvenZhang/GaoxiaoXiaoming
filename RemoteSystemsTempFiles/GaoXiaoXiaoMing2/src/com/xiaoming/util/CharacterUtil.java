/**
 * @author JJ
 * 字符串编码格式转换工具类
 */
package com.xiaoming.util;

import java.io.UnsupportedEncodingException;

public class CharacterUtil {
	public static String changeCharacter(String str,String character) throws UnsupportedEncodingException{
		return new String(str.getBytes(),character);
	}
}
