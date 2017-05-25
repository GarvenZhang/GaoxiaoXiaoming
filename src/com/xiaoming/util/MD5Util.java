package com.xiaoming.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5加密工具
 * @author Yunfei
 *
 */
public class MD5Util {
	public static String encode(String data){
		return DigestUtils.md5Hex(data);
	}
}
