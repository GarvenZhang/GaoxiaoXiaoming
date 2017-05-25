package com.xiaoming.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xiaoming.base.Role;
import com.xiaoming.constants.Constants;
import com.xiaoming.constants.ErrorConstants;
import com.xiaoming.domain.Activity;
import com.xiaoming.domain.Organization;
import com.xiaoming.domain.Pager;
import com.xiaoming.dto.ActivityDto;
import com.xiaoming.util.JsonUtil;
import com.xiaoming.util.StringUtil;
import com.xiaoming.util.SystemContext;
import com.xiaoming.util.authority.Authority;

import net.sf.json.JSONObject;

/**
 * 
 * @author Yunfei
 *
 */
@Controller
@Scope("prototype") 
public class ActivityAction extends MediaBaseAction<ActivityDto> {
	

	/**
	 * 添加一个报名信息
	 * @return
	 */
	@Authority(role=Role.ORG_USER)
	public String save(){
		try{
			//传入ClassMap
			model = getModelFromJson(ActivityDto.getClassMap());
			//把info从数组转成json键值对
			Map<String,String> info = new HashMap<>();
			for (String item : model.getInfos()) {
				info.put(item, "");
			}
			JSONObject json = new JSONObject();
			json.accumulateAll(info);
			model.setInfo(json.toString());
			//添加
			activityService.save(model, getCurrentMemberId());
			
			result = JsonUtil.succ();
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 处理一个报名
	 * @return
	 */
	@Authority(role=Role.ORG_USER)
	public String enroll(){
		try{
			JSONObject json = JSONObject.fromObject(getJsonFromRequest());
			String info = json.getJSONObject("info").toString();
			String id = json.getString("id");
			
			activityService.handle(Long.parseLong(id), info);
			
			result = JsonUtil.succ();
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询
	 * @return
	 */
	@Authority(role=Role.ORG_USER)
	public String list(){
		try{
			model = getModelFromJson();
			//设置分页参数
			SystemContext.setPageArgs(model.getPageSize(),model.getPageNum());
			
			Pager<Activity> pager = null;
			if(model.getType()==Constants.PUBLICSHED){ //查询发送的报名
				pager = activityService.findPublished(getCurrentMemberId());
			}else{ //查询收到的报名
				pager = activityService.findReceived(getCurrentMemberId());
			}
			
			//组装Dto
			Pager<ActivityDto> pagerDto = new Pager<>();
			List<ActivityDto> activityDtos = new ArrayList<>();
			for (Activity activity : pager.getRecordList()) {
				ActivityDto dto = new ActivityDto(activity);
				activityDtos.add(dto);
				if(model.getType()==Constants.PUBLICSHED){
					dto.setType(Constants.PUBLICSHED);
				}else{
					dto.setType(Constants.RECEIVED);
				}
			}
			pagerDto.setPageArgs(pager);
			pagerDto.setRecordList(activityDtos);
			
			result = JsonUtil.succObject(pagerDto,"state","members","delay","department","edition","grade","isAgree","isFinished","isRead","major","organization","phoneNumber","position");
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}
	
	@Authority(role=Role.ORG_USER)
	public String detial(){
		try{
			model = getModelFromJson();
			String id = model.getId();
			
			Activity activity = activityService.get(Long.parseLong(id));
			ActivityDto dto = new ActivityDto(activity);
			
			//要忽略的字段
			String[] ignore = {};
			
			result = JsonUtil.succObject(dto);
			
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		
		
		return SUCCESS;
	}
	
	public String download(){
		try{
			model = getModelFromJson();
			String id = model.getId();
			if(StringUtil.isNull(id)){
				result = JsonUtil.fail(ErrorConstants.ID_IS_NULL);
				return ERROR;
			}
			Activity activity = activityService.get(Long.parseLong(id));
			if(activity==null){
				result = JsonUtil.fail("该id对应的活动不存在");
				return ERROR;
			}
			stream = activityService.getEnrollInfoExcel(Long.parseLong(id));
			Organization org = organizationService.get(getOrgId());
			String fileName = org.getName()+"-"+activity.getTitle()+Constants.EXCEL;
			fileName = new String(fileName.getBytes(),"ISO8859-1");
			setFileName(fileName);
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
		return "excel";
	}
	
	public String info(){
		try{
			model = getModelFromJson();
			String idstr = model.getId();
			if(StringUtil.isNull(idstr)){
				result = JsonUtil.fail(ErrorConstants.ID_IS_NULL);
				return ERROR;
			}
			Long id = Long.parseLong(idstr);
			Activity activity = activityService.get(id);
			ActivityDto dto = new ActivityDto(activity);
			result = JsonUtil.succObject(dto,"type","enroll","state","members","delay","department","edition","grade","isAgree","isFinished","isRead","major","organization","phoneNumber","position");
		}catch(Exception e){
			
		}
		return SUCCESS;
	}
}
