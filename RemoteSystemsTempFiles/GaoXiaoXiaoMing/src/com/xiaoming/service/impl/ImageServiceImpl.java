package com.xiaoming.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoming.dao.ImageDao;
import com.xiaoming.dao.UserDao;
import com.xiaoming.domain.Image;
import com.xiaoming.service.ImageService;
import com.xiaoming.util.SaveFile;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {
	
	@Resource
	UserDao userDao;
	@Resource
	ImageDao imageDao;
	
	@Override
	public Image save(File file,String path,long userId) throws Exception{
		//生成
		Image image = new Image();
		image.setImageName(file.getName());
		image.setUploader(userDao.get(userId));
		image.setUploadTime(new Date());
		image.setUrl(path);
		
		return imageDao.save(image);
	}
	
	@Override
	public Image get(long id){
		return imageDao.get(id);
	}
	
}
