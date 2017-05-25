package com.xiaoming.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.zmj.ucpaas.JsonMessage;

/**
 * 发送短信验证码的工具
 * @author Yunfei
 *
 */
@Component
public class SendMessageUtil {
	
	@Value("${account_sid}")
	private static String account_sid;
	@Value("${auth_token}")
	private static String auth_token;
	@Value("${app_id}")
	private static String app_id;
	@Value("${template_id}")
	private static String template_id;
	@Value("${web}")
	private static String web;
	@Value("${codeLength}")
	private static int codeLength = 5;
	@Value("${codeDeadLine}")
	private static int codeDeadLine = 30;
	
	public static String sendCode(String phone){
		JsonMessage jm = new JsonMessage();
		return jm.sendJsonMessage(account_sid, auth_token, app_id, template_id, web, codeLength, codeDeadLine, phone);
	}
}
