package com.xiaoming.test.util;

import org.junit.Test;

import com.zmj.ucpaas.JsonMessage;
import com.zmj.util.CodeUtil;

public class MessageUtil {
	
	@Test
	public void sendCode(){
		JsonMessage jm = new JsonMessage();
		String account_sid	= "db10582ae20b54845098b171016d5dbf";
		String auth_token	= "32f3287e736b3f46d872e73228fc437d";
		String app_id		= "7a01c229143d4c8a910a2c11f7a86cb5";
		String template_id	= "5723";
		String web = "高校小明";
		int codeLength = 5;
		int codeDeadLine = 30;
		String Phone = "15626299517";
		
		String code = jm.sendJsonMessage(account_sid, auth_token, app_id, template_id, web, codeLength, codeDeadLine, Phone);
		System.out.println(code);
		
	}
	
}
