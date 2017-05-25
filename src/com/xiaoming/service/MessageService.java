package com.xiaoming.service;

import java.util.List;

import com.xiaoming.domain.Message;
import com.xiaoming.domain.Pager;

public interface MessageService {
	/**
	 * 添加一条
	 * @param message
	 * @param memberId
	 */
	public Message save(Message message,long[] reciversId,long memberId);
	/**
	 * 查询member收到的通知，同時將這些消息設置爲已讀
	 * @param memberId
	 * @return
	 */
	public Pager<Message> findReceived(long memberId);
	/**
	 * 查询member发出的通知
	 * @param memberId
	 * @return
	 */
	public Pager<Message> findPublished(long memberId);
	/**
	 * 更新
	 * @param message
	 * @return
	 */
	public Message update(Message message,long[] reciversId);
	/**
	 * 删除
	 * @param id
	 */
	public void delete(long id);
	/**
	 * 将list中的消息设置为已读
	 * @param idList UsersMessage的id
	 */
	public void read(List<Long> idList);
	/**
	 * 将list中的消息设置为已读
	 * @param idList Message的id
	 * @param memberId
	 */
	public void read(List<Long> idList,long memberId);

}
