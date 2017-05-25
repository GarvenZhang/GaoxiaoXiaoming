package com.xiaoming.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xiaoming.constants.Constants;
import com.xiaoming.dao.MessageDao;
import com.xiaoming.domain.Message;
import com.xiaoming.domain.Pager;
import com.xiaoming.dto.MessageDto;
import com.xiaoming.dto.UsersMessageDto;
import com.xiaoming.util.JsonUtil;
import com.xiaoming.util.SystemContext;

@Controller
@Scope("prototype")
public class MessageAction extends BaseAction<MessageDto> {

	public String list() {
		try {
			model = getModelFromJson();
			// 設置分頁
			SystemContext.setPageSize(model.getPageSize());
			SystemContext.setPageOffset(model.getPageNum());

			// 查詢響應類型的消息
			Pager<Message> messagePager;
			long memberId = getCurrentMemberId();
			if (model.getType() == Constants.RECEIVED) {
				messagePager = messageService.findReceived(memberId);
			} else {
				messagePager = messageService.findPublished(memberId);
			}

			// 轉成Json格式
			Pager<MessageDto> dtoPager = new Pager<>();
			dtoPager.setPageArgs(messagePager);
			List<MessageDto> dtoList = new ArrayList<>();
			List<Long> messageIdList = new ArrayList<>(dtoList.size());
			for (Message message : messagePager.getRecordList()) {
				dtoList.add(new MessageDto(message));
				messageIdList.add(message.getId());
			}
			//将查询到的消息设置为已读
			messageService.read(messageIdList, getCurrentMemberId());
			
			dtoPager.setRecordList(dtoList);

			result = JsonUtil.succObject(dtoPager);
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}

	public String save() {
		try {
			model = getModelFromJson(model.getClassMap());

			// 消息
			Message message = new Message();
			message.setContent(model.getContent());
			message.setPublishTime(new Date());
			message.setUpdateTime(new Date());

			// 接收者列表
			//新接口，传入接受消息的成员的id数组
			if (model.getMembers() != null && model.getMembers().length != 0) {
				String[] strIds = model.getMembers();
				long[] ids = new long[strIds.length];
				for (int i = 0; i < ids.length; i++) {
					ids[i] = Long.parseLong(strIds[i]);
				}
				message = messageService.save(message, ids, getCurrentMemberId());
			} else { //旧接口
				List<UsersMessageDto> receiverList = model.getReceivers();
				long[] ids = new long[receiverList.size()];
				for (int i = 0; i < ids.length; i++) {
					ids[i] = Long.parseLong(receiverList.get(i).getMemberId());
				}
				message = messageService.save(message, ids, getCurrentMemberId());
			}

			result = JsonUtil.succObject(new MessageDto(message));
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}

	public String update() {
		try {
			model = getModelFromJson(model.getClassMap());

			// 消息
			Message message = new Message();
			message.setContent(model.getContent());
			message.setId(Long.parseLong(model.getId()));

			// 接收者列表
			List<UsersMessageDto> receiverList = model.getReceivers();
			long[] ids = new long[receiverList.size()];
			for (int i = 0; i < ids.length; i++) {
				ids[i] = Long.parseLong(receiverList.get(i).getMemberId());
			}

			message = messageService.update(message, ids);
			result = JsonUtil.succObject(new MessageDto(message));
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			model = getModelFromJson(model.getClassMap());

			messageService.delete(Long.parseLong(model.getId()));

			result = JsonUtil.succ();
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}
}
