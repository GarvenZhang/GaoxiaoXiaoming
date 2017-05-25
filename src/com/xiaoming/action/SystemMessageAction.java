package com.xiaoming.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xiaoming.domain.Pager;
import com.xiaoming.domain.SystemMessage;
import com.xiaoming.dto.SystemMessageDto;
import com.xiaoming.util.JsonUtil;
import com.xiaoming.util.SystemContext;

@Controller
@Scope("prototype") 
public class SystemMessageAction extends BaseAction<SystemMessageDto> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String list(){
		try{
			model = getModelFromJson(); 
			SystemContext.setPageSize(model.getPageSize());
			SystemContext.setPageOffset(model.getPageNum());
			
			Pager<SystemMessage> pager = systemMessageService.list(getCurrentUserId());
			Pager<SystemMessageDto> pagerDto = new Pager<>();
			List<SystemMessageDto> dtoList = new ArrayList<>();
			pagerDto.setRecordList(dtoList);
			pagerDto.setPageArgs(pager);
			for (SystemMessage systemMessage : pager.getRecordList()) {
				dtoList.add(new SystemMessageDto(systemMessage));
			}
			
			result = JsonUtil.succObject(pagerDto);
			
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}
	
}
