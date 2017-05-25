 package com.xiaoming.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xiaoming.domain.FeedBack;

@Controller
@Scope("prototype") 
public class FeedBackAction extends BaseAction<FeedBack> {
	
	public String add(){
		
		return "success";
	}
	
}
