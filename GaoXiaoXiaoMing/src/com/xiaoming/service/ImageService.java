package com.xiaoming.service;

import java.io.File;
import java.io.InputStream;

import com.xiaoming.domain.Image;

public interface ImageService {
	
	/**
	 * 上传
	 * @param file
	 * @param userId
	 * @throws Exception 
	 */
	public Image save(File file,String path,long userId) throws Exception;
	/**
	 * 获取
	 * @param id
	 * @return
	 */
	public Image get(long id);
}
