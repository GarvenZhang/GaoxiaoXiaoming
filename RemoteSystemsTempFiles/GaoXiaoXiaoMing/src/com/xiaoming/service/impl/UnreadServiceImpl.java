package com.xiaoming.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoming.dao.MemberDao;
import com.xiaoming.dao.UnreadDao;
import com.xiaoming.domain.Member;
import com.xiaoming.domain.Unread;
import com.xiaoming.dto.UnreadDto;
import com.xiaoming.dto.UserUnreadDto;
import com.xiaoming.service.UnreadService;

@Service
@Transactional
public class UnreadServiceImpl implements UnreadService{
	
	@Resource
	UnreadDao unreadDao;
	@Resource
	MemberDao memberDao;

	@Override
	public void add(long id, int type) {
		Unread count = unreadDao.get(id);
		if(null == count){
			count = new Unread();
			Member member = memberDao.get(id);
			count.setMember(member);
			count.add(type);
			unreadDao.save(count);
		}else{
			count.add(type);
			unreadDao.update(count);
		}
	}

	@Override
	public void clear(long id, int type) {
		Unread count = unreadDao.get(id);

		count.clear(type);
		unreadDao.update(count);
	}

	@Override
	public Unread getByMember(long id) {
		return unreadDao.get(id);
	}

	@Override
	public UserUnreadDto getByUser(long id) {
		//查询用户所有成员的未读消息
		String hql = "from Unread u where u.member.user.id= :id";
		Map<String,Object> alias = new HashMap<>();
		alias.put("id", id);
		List<Unread> unreadList = unreadDao.list(hql, alias);
		
		//转成dto
		ArrayList<UnreadDto> dtoList = new ArrayList<>();
		for (Unread unread : unreadList) {
			dtoList.add(new UnreadDto(unread));
		}
		
		//查询未读系统消息的数量
		hql = "select count(*) from SystemMessageUser sysmu where sysmu.read=false and sysmu.user.id= :id";
		Long count = (Long) unreadDao.queryObject(hql, alias);
		UserUnreadDto userUnreadDto = new UserUnreadDto();
		userUnreadDto.setMembers(dtoList);
		userUnreadDto.setSystemMessage(count);
		
		return userUnreadDto;
	}

}
