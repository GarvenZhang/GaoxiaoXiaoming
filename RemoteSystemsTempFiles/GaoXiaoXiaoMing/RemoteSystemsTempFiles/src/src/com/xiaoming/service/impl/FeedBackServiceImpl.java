package com.xiaoming.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoming.dao.FeedBackDao;
import com.xiaoming.domain.FeedBack;
import com.xiaoming.service.FeedBackService;

@Service
@Transactional
public class FeedBackServiceImpl implements FeedBackService {
	
	@Resource
	private FeedBackDao feedBackDao;

	@Override
	public void send(FeedBack feedback) {
		feedBackDao.save(feedback);
	}

	@Override
	public void relpy(Long id, String reply) {
		FeedBack feedBack = feedBackDao.get(id);
		feedBack.setReply(reply);
		feedBackDao.update(feedBack);
	}

}
