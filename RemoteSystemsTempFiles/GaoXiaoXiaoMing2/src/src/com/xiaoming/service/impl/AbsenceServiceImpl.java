package com.xiaoming.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoming.constants.Constants;
import com.xiaoming.dao.AbsenceApplyDao;
import com.xiaoming.dao.AbsenceDao;
import com.xiaoming.dao.MemberDao;
import com.xiaoming.domain.Absence;
import com.xiaoming.domain.AbsenceApply;
import com.xiaoming.domain.Member;
import com.xiaoming.domain.Pager;
import com.xiaoming.dto.MemberDto;
import com.xiaoming.service.AbsenceService;
import com.xiaoming.service.UnreadService;

@Service
@Transactional
public class AbsenceServiceImpl implements AbsenceService {
	
	@Resource
	private UnreadService unreadService;
	@Resource
	private MemberDao memberDao;
	@Resource
	private AbsenceDao absenceDao;
	@Resource
	private AbsenceApplyDao absenceApplyDao;
	
	@Override
	@Deprecated
	public void save(Absence absence, List<MemberDto> receiver, long memberId) {
		long[] receiverIds = new long[receiver.size()];
		for (int i = 0; i < receiver.size(); i++) {
			receiverIds[i] = Long.parseLong(receiver.get(i).getId());
		}
		save(absence, receiverIds, memberId);
	}
	
	@Override
	public void save(Absence absence, String[] receiver, long memberId) {
		long[] receiverIds = new long[receiver.length];
		for (int i = 0; i < receiver.length; i++) {
			receiverIds[i] = Long.parseLong(receiver[i]);
		}
		save(absence, receiverIds, memberId);
	}
	
	@Override
	public void save(Absence absence, long[] receiver, long memberId) {
		Member publisher = memberDao.get(memberId);
		//每一个接收者都创建一个对应的申请
		Set<AbsenceApply> applySet = new HashSet<>();
		for (long receiverId : receiver) {
			Member member = memberDao.get(receiverId);
			//创建一个申请
			AbsenceApply apply = new AbsenceApply();
			apply.setAbsence(absence);
			apply.setReceiver(member);
			//将申请添加到集合
			applySet.add(apply);
			
			//为成员member添加一条未读消息
			unreadService.add(member.getId(), Constants.UNREAD_ABSENCE);
		}
		absence.setMember(publisher);
		absence.setAbsenceApply(applySet);
		absenceDao.save(absence);
	}

	@Override
	public Pager<Absence> findPublished(Member currentMember) {
		String hql = "from Absence absence where absence.member.id =:id";
		Map<String,Object> alias = new HashMap<>();
		alias.put("id", currentMember.getId());
		return absenceDao.findByAlias(hql, alias);
	}
	
	@Override
	public Pager<Absence> findPublished(long memberId) {
		String hql = "from Absence absence where absence.member.id =:id";
		Map<String,Object> alias = new HashMap<>();
		alias.put("id", memberId);
		return absenceDao.findByAlias(hql, alias);
	}

	@Override
	public Pager<Absence> findReceived(Member currentMember) {
		String hql = "from Absence absence where (select apply.receiver.id from absence.absenceApply apply)=:id";
		Map<String,Object> alias = new HashMap<>();
		alias.put("id", currentMember.getId());
		//清除该成员请假中的未读消息
		unreadService.clear(currentMember.getId(), Constants.UNREAD_ABSENCE);
		//返回查询结果
		return absenceDao.findByAlias(hql, alias);
	}

	@Override
	public Pager<Absence> findReceived(long memberId) {
		String hql = "from Absence absence where :id in (select apply.receiver.id from absence.absenceApply apply)";
		Map<String,Object> alias = new HashMap<>();
		alias.put("id", memberId);
		//清除该成员请假中的未读消息
		unreadService.clear(memberId, Constants.UNREAD_ABSENCE);
		//返回查询结果
		return absenceDao.findByAlias(hql, alias);
	}
	
	@Override
	public void handle(long id, boolean agree) {
		AbsenceApply apply = absenceApplyDao.get(id);
		apply.setHandleTime(new Date());
		apply.setIsHandled(true);
		apply.setIsAgree(agree);
		absenceApplyDao.update(apply);
	}

	@Override
	public void update(Absence newAbsence, List<MemberDto> receiver) {
		Absence absence = absenceDao.get(newAbsence.getId());
		
		Set<AbsenceApply> applySet = new HashSet<>();
		for (MemberDto memberDto : receiver) {
			Member member = memberDao.get(Long.parseLong(memberDto.getId()));
			//创建一个申请
			AbsenceApply apply = new AbsenceApply();
			apply.setAbsence(absence);
			apply.setReceiver(member);
			//将申请添加到集合
			applySet.add(apply);
		}
		
		absence.setAbsenceApply(applySet);
		absence.setContent(newAbsence.getContent());
		absence.setAbsenceTime(newAbsence.getAbsenceTime());
		absence.setUpdateTime(new Date());
		absenceDao.update(absence);
	}

	@Override
	public void delete(long id) {
		absenceDao.delete(id);
	}




}
