package com.xiaoming.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xiaoming.constants.ErrorConstants;
import com.xiaoming.dto.ErrorDto;
import com.xiaoming.util.JsonUtil;

@Controller
@Scope("prototype") 
public class ErrorAction extends BaseAction<ErrorDto> {
	
	public String errorMessage;
	
	public String noLogin(){
		result = JsonUtil.fail(ErrorConstants.NO_LOGIN);
		return SUCCESS;
	}
	
	public String noPermission(){
		result = JsonUtil.fail(ErrorConstants.AUTHORITY);
		return SUCCESS;
	}
	
	public String noOrg(){
		result = JsonUtil.fail(ErrorConstants.NONE_ORG);
		return SUCCESS;
	}
	
	public String execute(){
		result = JsonUtil.fail(getErrorMessage());
		return SUCCESS;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
