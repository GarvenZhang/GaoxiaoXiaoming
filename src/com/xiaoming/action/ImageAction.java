package com.xiaoming.action;

import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.xiaoming.domain.Image;
import com.xiaoming.dto.ImageDto;
import com.xiaoming.dto.UserInfoDto;
import com.xiaoming.util.JsonUtil;
import com.xiaoming.util.RandomString;
import com.xiaoming.util.SaveFile;

@Controller
@Scope("prototype") 
public class ImageAction extends MediaBaseAction<ImageDto> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String upload(){
		try{
			String path = "upload\\image";
			// 重新设置文件名称
			setFileFileName("\\" + RandomString.createRandoString() + ".jpg");
			// logo文件路径
			String rootPath = ((ServletContext)ActionContext.getContext().get(ServletActionContext.SERVLET_CONTEXT)).getRealPath("/");
			String filePath = rootPath + path + getFileFileName();
			//保存图片
			SaveFile.save(file, filePath);
			Image image = imageService.save(file, path + getFileFileName() , getCurrentUserId());
			
			ImageDto dto = new ImageDto();
			dto.setId(image.getId().toString());
			dto.setUrl(image.getUrl());
			result = JsonUtil.succObject(dto);
		}catch(IOException e){
			result = JsonUtil.fail("file");
			e.printStackTrace();
			return ERROR;
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}finally {
			
		}
		return SUCCESS;
	}
	
}
