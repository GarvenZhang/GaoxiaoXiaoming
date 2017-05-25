package com.xiaoming.action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xiaoming.base.Role;
import com.xiaoming.constants.Constants;
import com.xiaoming.domain.Organization;
import com.xiaoming.domain.Pager;
import com.xiaoming.dto.DataStatisticDto;
import com.xiaoming.util.JsonUtil;
import com.xiaoming.util.SystemContext;
import com.xiaoming.util.authority.Authority;

@Repository
public class StatisticAction extends MediaBaseAction<DataStatisticDto> {
	
	/**
	 * 获取列表
	 * @return
	 */
	@Authority(role=Role.ORG_ADMIN)
	public String list(){
		try{
			model = getModelFromJson();
			//设置分页参数
			SystemContext.setPageArgs(model.getPageSize(),model.getPageNum());
			//处理时间
			int days = model.getDaysAgo();
			if(days == 0) days = 30;
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			Date now = calendar.getTime();
			calendar.add(Calendar.DAY_OF_YEAR, -days); //
			Date begin = calendar.getTime();
			//统计
			Pager<DataStatisticDto> dsDto = statisticService.list(getOrgId(),begin,now);
			result = JsonUtil.succObject(dsDto);
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 跟据姓名查询
	 * @return
	 */
	@Authority(role=Role.ORG_ADMIN)
	public String query(){
		try{
			model = getModelFromJson();
			int days = model.getDaysAgo();
			if(days == 0) days = 30;
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			Date now = calendar.getTime();
			calendar.add(Calendar.DAY_OF_YEAR, -days); 
			Date begin = calendar.getTime();
			List<DataStatisticDto> dsList = statisticService.query(model.getName(), getOrgId(),begin,now);
			if(dsList==null || dsList.size() == 0){
				result = JsonUtil.fail("该成员不存在");
				return ERROR;
			}
			result = JsonUtil.succList(dsList);
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 下载统计表
	 * @return
	 */
	@Authority(role=Role.ORG_ADMIN)
	public String download(){
		try{
			stream = statisticService.download(getOrgId());
			Organization org = organizationService.get(getOrgId());
			String fileName = org.getName()+Constants.EXCEL_DATA_STATISTIC_FILENAME+Constants.EXCEL;
			fileName = new String(fileName.getBytes(),"ISO8859-1");
			setFileName(fileName);
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail(e.getMessage());
			return ERROR;
		}
		return "excel";
	}
}
