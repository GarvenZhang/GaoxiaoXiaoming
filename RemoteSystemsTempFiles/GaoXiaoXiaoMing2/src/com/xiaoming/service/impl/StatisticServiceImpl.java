package com.xiaoming.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoming.base.impl.BaseDaoImpl;
import com.xiaoming.constants.Constants;
import com.xiaoming.dao.MemberDao;
import com.xiaoming.domain.Absence;
import com.xiaoming.domain.Assignment;
import com.xiaoming.domain.AssignmentMember;
import com.xiaoming.domain.Member;
import com.xiaoming.domain.Pager;
import com.xiaoming.dto.DataStatisticDto;
import com.xiaoming.service.StatisticService;
import com.xiaoming.util.ExcelMaker;
import com.xiaoming.util.SystemContext;

@Service
@Transactional
public class StatisticServiceImpl implements StatisticService {
	
	@Resource
	MemberDao memberDao;
	@Resource
	BaseDaoImpl baseDao;
	@Resource
	SessionFactory sessionFactory;

	@Override
	public Pager<DataStatisticDto> list(long orgId,Date begin,Date end) {
		
		Pager<DataStatisticDto> statisticPager = new Pager<>();

		if (null == SystemContext.getSort() || "".equals(SystemContext.getSort())) {
			SystemContext.setSort("m.department.name");
		}
		// 查询该组织中的成员
		String hql = "from Member m where m.department.orgBelong.id= :id";
		Map<String, Object> alias = new HashMap<>();
		alias.put("id", orgId);
		Pager<Member> mPager = memberDao.findByAlias(hql, alias);
		// 初始化统计列表
		statisticPager.setPageArgs(mPager);
		// 计算统计结果
		List<DataStatisticDto> dsList = statisticList(mPager.getRecordList(), begin, end);
		statisticPager.setRecordList(dsList);
		return statisticPager;
	}
	
	public DataStatisticDto query(String name,long orgId){
		String hql = "from Member m where m.department.orgBelong.id= :orgId and m.user.realName= :name";
		Map<String,Object> alias = new HashMap<>();
		alias.put("orgId", orgId);
		alias.put("name", name);
		Member member =(Member) memberDao.queryObject(hql, alias);
		if(member==null){ //用户不存在
			return null;
		}
		
		DataStatisticDto dsDto = new DataStatisticDto();
		Set<Absence> absenceSet = member.getPublishedAbsences();
		Set<Assignment> publishedAssignment = member.getPublishedAssignment();

		Set<AssignmentMember> amSet = member.getReceivedAssignment();
		//完成工作数，逾期数，未完成数
		int finish=0,outOfDate=0,imperfect = 0;
		for (AssignmentMember am : amSet) {
			if(am.getIsFinished()){ //已完成
				finish++;
				//逾期
				if(am.getFinishTime().getTime()>am.getAssignment().getDeadline().getTime()){
					outOfDate++;
				}
			}else{ //未完成
				imperfect++;
			}
		}

		dsDto.setName(member.getUser().getRealName());
		dsDto.setReceived(amSet==null?0:amSet.size());
		dsDto.setAbsence(absenceSet == null?0:absenceSet.size());
		dsDto.setPublished(publishedAssignment==null?0:publishedAssignment.size());
		dsDto.setFinished(finish);
		dsDto.setImperfect(imperfect);
		dsDto.setOutOfDate(outOfDate);
		dsDto.setDepartment(member.getDepartment().getName());

		return dsDto;
	}
	
	public List<DataStatisticDto> query(String name,Long orgId,Date begin,Date end){
		String hql = "from Member m where m.user.realName= :name and m.department.orgBelong.id="+orgId;
		Map<String ,Object> alias = new HashMap<>();
		alias.put("name", name);
		List<Member> memberList = memberDao.list(hql, alias);
		
		return statisticList(memberList,begin,end);
	}

	@Override
	public InputStream download(long orgId) {
		String hql = "from Member m where m.department.orgBelong.id= :id";
		Map<String,Object> alias = new HashMap<>();
		alias.put("id", orgId);
		
		List<Member> mList = memberDao.list(hql, alias);
		List<DataStatisticDto> dsList = statisticList(mList); //统计
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		new ExcelMaker<DataStatisticDto>().exportExcel(Constants.EXCEL_DATA_STATSITC, dsList, out);
		return new ByteArrayInputStream(out.toByteArray());
	}
	
	/**
	 * 统计一个成员的数据
	 * @param member
	 * @return
	 */
	private DataStatisticDto statistic(Member member){
		DataStatisticDto dsDto = new DataStatisticDto();
		
		Set<AssignmentMember> amSet = member.getReceivedAssignment();
		//完成工作数，逾期数，未完成数
		int finish=0,outOfDate=0,imperfect = 0;
		for (AssignmentMember am : amSet) {
			if(am.getIsFinished()){ //已完成
				finish++;
				//逾期
				if(am.getFinishTime().getTime()>am.getAssignment().getDeadline().getTime()){
					outOfDate++;
				}
			}else{ //未完成
				imperfect++;
			}
		}
		
		dsDto.setName(member.getUser().getRealName());
		dsDto.setDepartment(member.getDepartment().getName());
		dsDto.setPublished(member.getPublishedAssignment().size());
		dsDto.setReceived(amSet.size());
		dsDto.setFinished(finish);
		dsDto.setImperfect(imperfect);
		dsDto.setOutOfDate(outOfDate);
		dsDto.setAbsence(member.getPublishedAbsences().size());
		
		return dsDto;
	}
	
	/**
	 * 统计List中所有成员的数据
	 * @param mList
	 * @return
	 */
	private List<DataStatisticDto> statisticList(List<Member> mList){
		ArrayList<DataStatisticDto> dsList = new ArrayList<>();
		for (Member member : mList) {
			dsList.add(statistic(member));
		}
		return dsList;
	}
	
	/**
	 * 统计一个成员的数据（如果没有传session，则自动从sessionFactory中获取一个session）
	 * @param member
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	private DataStatisticDto statistic(Member member, Date beginTime, Date endTime){
		Session session = sessionFactory.getCurrentSession();
		return statistic(member, beginTime, endTime, session);
	}
	
	/**
	 * 统计一个成员的数据
	 * @param member	要统计的成员
	 * @param beginTime 开始时间
	 * @param endTime	结束时间
	 * @param session	Hibernate的Session
	 * @return
	 */
	private DataStatisticDto statistic(Member member, Date beginTime, Date endTime, Session session){
		DataStatisticDto dsDto = new DataStatisticDto();
		//收到的任务数量
		Query query = session.createQuery(
				  " select count(*) "
				+ " from AssignmentMember am "
				+ " where am.exerciser.id="+member.getId()
				+ " and am.assignment.publishDate >= :beginTime "
				+ " and am.assignment.publishDate <= :endTime");
		query.setTimestamp("beginTime",beginTime);
		query.setTimestamp("endTime", endTime);
		dsDto.setReceived((Long)query.uniqueResult());
		//发布的任务数量
		query = session.createQuery(
				"select count(*) "
				+ " from Assignment a "
				+ " where a.publisher.id="+member.getId()
				+ " and a.publishDate >= :beginTime "
				+ " and a.publishDate <= :endTime");
		query.setTimestamp("beginTime",beginTime);
		query.setTimestamp("endTime", endTime);
		dsDto.setPublished((Long)query.uniqueResult());
		//请假数量
		query = session.createQuery(
				"select count(*) "
				+ " from Absence absence "
				+ " where absence.member.id="+member.getId()
				+ " and absence.absenceTime >= :beginTime "
				+ " and absence.absenceTime <= :endTime");
		query.setTimestamp("beginTime",beginTime);
		query.setTimestamp("endTime", endTime);
		dsDto.setAbsence((Long)query.uniqueResult());
		//完成的任务数量
		query = session.createQuery(
				"select count(*) "
				+ " from AssignmentMember "
				+ " where exerciser.id="+member.getId()
				+ " and isFinished=true "
				+ " and finishTime >= :beginTime "
				+ " and finishTime <= :endTime");
		query.setTimestamp("beginTime",beginTime);
		query.setTimestamp("endTime", endTime);
		dsDto.setFinished((Long)query.uniqueResult());
		//逾期的任务数量
		query = session.createQuery(
				"select count(*) "
				+ " from AssignmentMember "
				+ " where exerciser.id="+member.getId()
				+ 	" and isFinished=true "
				+ 	" and finishTime>assignment.deadline "
				+ 	" and assignment.deadline >= :beginTime"
				+ 	" and assignment.deadline <= :endTime");
		query.setTimestamp("beginTime",beginTime);
		query.setTimestamp("endTime", endTime);
		dsDto.setOutOfDate((Long)query.uniqueResult());
		//未完成的任务数量
		query = session.createQuery(
				"select count(*) "
				+ " from AssignmentMember "
				+ " where exerciser.id="+member.getId()
				+ 	" and isFinished=false "
				+ 	" and assignment.deadline >= :beginTime"
				+ 	" and assignment.deadline <= :endTime");
		query.setTimestamp("beginTime",beginTime);
		query.setTimestamp("endTime", endTime);
		dsDto.setImperfect((Long)query.uniqueResult());
		
		dsDto.setId(member.getId().toString());
		dsDto.setName(member.getUser().getRealName());
		dsDto.setDepartment(member.getDepartment().getName());
		
		return dsDto;
	}
	
	/**
	 * 统计从beginTime到endTime这段时间内List中的成员的数据
	 * @param memberList
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	private List<DataStatisticDto> statisticList(List<Member> memberList,Date beginTime,Date endTime){
		Session session = sessionFactory.getCurrentSession();
		List<DataStatisticDto> dsList = new ArrayList<>(memberList.size());
		for (Member member : memberList) {
			dsList.add(statistic(member, beginTime, endTime, session));
		}
		return dsList;
	}
	
}
