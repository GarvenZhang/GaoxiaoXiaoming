package com.xiaoming.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.xiaoming.constants.Constants;
import com.xiaoming.dao.ActivityDao;
import com.xiaoming.dao.ActivityEnrollDao;
import com.xiaoming.dao.MemberDao;
import com.xiaoming.domain.Activity;
import com.xiaoming.domain.ActivityEnroll;
import com.xiaoming.domain.Member;
import com.xiaoming.domain.Pager;
import com.xiaoming.dto.ActivityDto;
import com.xiaoming.service.ActivityService;
import com.xiaoming.service.UnreadService;
import com.xiaoming.util.ExcelUtil;
import com.xiaoming.util.SystemContext;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

	@Resource
	MemberDao memberDao;
	@Resource
	ActivityDao activityDao;
	@Resource
	ActivityEnrollDao activityEnrollDao;
	@Resource
	private UnreadService unreadService;
	
	@Override
	public void save(ActivityDto aDto,long currentMemberId) {
		Member currentMember = memberDao.get(currentMemberId);
		
		//创建活动
		Activity activity = new Activity();
		activity.setTitle(aDto.getTitle());
		activity.setContent(aDto.getContent());
		activity.setInfo(aDto.getInfo());
		activity.setCreateTime(new Date());
		activity.setCreater(currentMember);
		activity.setOrganization(currentMember.getDepartment().getOrgBelong());
		//创建报名信息
		Set<ActivityEnroll> enrollSet = new HashSet<>();
		for (String memberId : aDto.getMemberId()) {
			ActivityEnroll enroll = new ActivityEnroll();
			enroll.setActivity(activity);
			enroll.setIsHandled(false);
			enroll.setMember(memberDao.get(Long.parseLong(memberId)));
			enroll.setInfo(aDto.getInfo());
			//添加进集合
			enrollSet.add(enroll);
			
			//为成员member添加一条未读消息
			unreadService.add(Long.parseLong(memberId), Constants.UNREAD_ACTIVITY);
		}
		activity.setActivityEnrolls(enrollSet);
		
		//添加
		activityDao.save(activity);
	}
	
	@Override
	public void handle(long id,String info){
		ActivityEnroll enroll = activityEnrollDao.get(id);
		//更新信息
		enroll.setInfo(info);
		enroll.setIsHandled(true);
		enroll.setEnrollTime(new Date());
		//保存
		activityEnrollDao.update(enroll);
	}

	@Override
	public Pager<Activity> findPublished(long memberId) {
		String hql = "from Activity activity where activity.creater.id= :createrId";
		SystemContext.setSort("activity.createTime");
		Map<String,Object> alias = new HashMap<>();
		alias.put("createrId", memberId);
		return activityDao.findByAlias(hql, alias);
	}

	@Override
	public Pager<Activity> findReceived(long memberId) {
		String hql = "from Activity activity where :memberId in (select enroll.member.id from activity.activityEnrolls enroll)";
		SystemContext.setSort("activity.createTime");
		Map<String,Object> alias = new HashMap<>();
		alias.put("memberId", memberId);
		//清除该成员的活动未读消息
		unreadService.clear(memberId, Constants.UNREAD_ACTIVITY);
		//返回查询结果
		return activityDao.findByAlias(hql, alias);
	}
	
	@Override
	public Activity get(long id){
		return activityDao.get(id);
	}
	
	@Override
	public InputStream getEnrollInfoExcel(Long id){
		//获取要导出的活动
		Activity activity = activityDao.get(id);
		//获取报名信息
		Set<ActivityEnroll> enrolls = activity.getActivityEnrolls();
		
		//
		List<HashMap> mapList = new ArrayList<>();
		for (ActivityEnroll enroll : enrolls) {
			HashMap infoMap = new Gson().fromJson(enroll.getInfo(), HashMap.class);
			infoMap.put("姓名", enroll.getMember().getUser().getRealName());
			infoMap.put("部门",enroll.getMember().getDepartment().getName());
			
			mapList.add(infoMap);
		}
		//创建excel表
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		new ExcelUtil<>().exportExcelFromMap(mapList, out);
		return new ByteArrayInputStream(out.toByteArray());
	}
}
