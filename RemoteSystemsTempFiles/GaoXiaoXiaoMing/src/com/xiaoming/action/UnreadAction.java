package com.xiaoming.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xiaoming.domain.Unread;
import com.xiaoming.dto.UnreadDto;
import com.xiaoming.dto.UserUnreadDto;
import com.xiaoming.util.JsonUtil;

@Controller
@Scope("prototype") 
public class UnreadAction extends BaseAction<UnreadDto>{
	
	public String query(){
		try{
			model = getModelFromJson();
			
			if(model.getId()==null || "".equals(model.getId())){ //如果没有收到id，查当前用户的
				UserUnreadDto unreadDto = unreadService.getByUser(getCurrentUserId());				
				result = JsonUtil.succObject(unreadDto);
			}else{	//收到的id，查该id对应成员的
				Unread unread = unreadService.getByMember(Long.parseLong(model.getId()));
				UnreadDto unreadDto = new UnreadDto(unread);
				result = JsonUtil.succObject(unreadDto);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}
	
}
