package com.xiaoming.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoming.constants.Constants;
import com.xiaoming.dao.MemberDao;
import com.xiaoming.dao.MessageDao;
import com.xiaoming.dao.UsersMessageDao;
import com.xiaoming.domain.Member;
import com.xiaoming.domain.Message;
import com.xiaoming.domain.Pager;
import com.xiaoming.domain.UsersMessage;
import com.xiaoming.service.MessageService;
import com.xiaoming.service.UnreadService;
import com.xiaoming.util.SystemContext;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
	@Resource
	MemberDao memberDao;
	@Resource
	MessageDao messageDao;
	@Resource
	UnreadService unreadService;
	@Resource
	UsersMessageDao usersMessageDao;

	@Override
	public Message save(Message message,long[] receiverIds, long memberId) {
		message.setPublisher(memberDao.get(memberId));
		message.setDeleted(false);
		
		Set<UsersMessage> receivers = new HashSet<>();
		//添加给成员
		for (long id : receiverIds) {
			Member member = memberDao.get(id);
			UsersMessage um = new UsersMessage();
			um.setReceiver(member);
			um.setIsRead(false);
			um.setMessage(message);
			receivers.add(um);
			//添加未读消息
			unreadService.add(id, Constants.UNREAD_MESSAGE);
		}
		
		message.setUsersMessage(receivers);

		return messageDao.save(message);
	}

	@Override
	public Pager<Message> findReceived(long memberId) {
		SystemContext.setSort("message.publishTime");
		String hql = "from Message message where :id in (select um.receiver.id from message.usersMessage um) and message.deleted=false";
		Map<String, Object> alias = new HashMap<>();
		alias.put("id", memberId);
		Pager<Message> messagePager = messageDao.findByAlias(hql, alias);
		// 删除未读消息
		unreadService.clear(memberId, Constants.UNREAD_MESSAGE);
		// 設置爲已讀
//		for (Message message : messagePager.getRecordList()) {
//			messageDao.executeUpdate(
//					"update UsersMessage um set um.isRead=true where um.message.id=? and um.receiver.id=?",
//					new Long[] { message.getId(), memberId });
//		}
		return messagePager;
	}
	
	/**
	 * 设置成已读状态
	 */
	@Override
	public void read(List<Long> idList){
		Session session = messageDao.getSession();
		String hql = "update UsersMessage um set um.isRead=true where um.id in (?)";
		Query query = session.createQuery(hql);
		query.setParameter(0, idList);
		query.executeUpdate();
	}
	
	/**
	 * 设置成已读状态
	 */
	@Override
	public void read(List<Long> idList, long memberId){
//		String hql = "update UsersMessage um set um.isRead=true where um.receiver.id= :memberId and um.message.id in :idList";
//		Map<String,Object> alias = new HashMap<>();
//		alias.put("memberId", memberId);
//		alias.put("idList", idList);
//		messageDao.executeUpdateByAlias(hql,alias);
		for (Long id : idList) {
			messageDao.executeUpdate(
					"update UsersMessage um set um.isRead=true where um.message.id=? and um.receiver.id=?",
					new Long[] { id, memberId });			
		}
	}

	@Override
	public Pager<Message> findPublished(long memberId) {
		SystemContext.setSort("message.publishTime");
		String hql = "from Message message where message.publisher.id= :id and message.deleted=false";
		Map<String, Object> alias = new HashMap<>();
		alias.put("id", memberId);
		return messageDao.findByAlias(hql, alias);
	}

	@Override
	public Message update(Message newMessage,long[] receiverIds) {
		Message message = messageDao.get(newMessage.getId());
		
		message.setContent(newMessage.getContent());
		message.setUpdateTime(new Date());
		
		//删除旧的成员，添加新的成员
		for (UsersMessage um : message.getUsersMessage()) {
			usersMessageDao.delete(um.getId());
		}

		Set<UsersMessage> receivers = new HashSet<>();
		//添加给成员
		for (long id : receiverIds) {
			Member member = memberDao.get(id);
			UsersMessage um = new UsersMessage();
			um.setReceiver(member);
			um.setIsRead(false);
			um.setMessage(message);
			receivers.add(um);
			//添加未读消息
			unreadService.add(id, Constants.UNREAD_MESSAGE);
		}
		message.setUsersMessage(receivers);
		
		messageDao.update(message);
		return message;
	}
	
	@Override
	public void delete(long id) {
		messageDao.executeUpdate("update Message message set message.deleted=true where message.id=?",
				new Long[] { id });
	}

}
