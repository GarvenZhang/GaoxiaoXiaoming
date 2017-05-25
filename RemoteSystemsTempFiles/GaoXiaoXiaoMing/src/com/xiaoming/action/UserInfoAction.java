package com.xiaoming.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.xiaoming.constants.ErrorConstants;
import com.xiaoming.domain.Image;
import com.xiaoming.domain.Member;
import com.xiaoming.dto.MemberDto;
import com.xiaoming.dto.UserInfoDto;
import com.xiaoming.util.JsonUtil;
import com.xiaoming.util.RandomString;
import com.xiaoming.util.SaveFile;
import com.xiaoming.util.StringUtil;

@Controller
@Scope("prototype") 
public class UserInfoAction extends MediaBaseAction<UserInfoDto> {

	/**
	 * 展示
	 * 
	 * @return
	 */
	public String show() {
		try {
			Member member = memberService.get(getCurrentMemberId());
			UserInfoDto userInfoDto = new UserInfoDto(member);
			result = JsonUtil.succObject(userInfoDto,"campusList");
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 更新信息
	 * @return
	 */
	public String update(){
		try{
			model = getModelFromJson();
			if(!StringUtil.isPhoneNumber(model.getPhoneNumber())){
				result = JsonUtil.fail(ErrorConstants.PHONE);
				return ERROR;
			}
			Member member = userService.update(model, getCurrentMemberId());
			result = JsonUtil.succObject(new UserInfoDto(member));
		}catch(Exception e){
			result = JsonUtil.fail();
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String updateLogo(){
		try{
			String path = getSavePath();
			// 重新设置文件名称
			setFileFileName("\\" + RandomString.createRandoString() + ".jpg");
			// logo文件路径
			String rootPath = ((ServletContext)ActionContext.getContext().get(ServletActionContext.SERVLET_CONTEXT)).getRealPath("/");
			String filePath = rootPath.substring(0, rootPath.length()-1) + path + getFileFileName();
			//保存图片
			SaveFile.save(file, filePath);
			Image image = imageService.save(file, path + getFileFileName() , getCurrentUserId());
			
			UserInfoDto dto = new UserInfoDto();
			dto.setLogoUrl(image.getUrl());
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
	
	public String memberList(){
		try{
			List<Member> memberList = userService.getMembers(getCurrentUserId());
			List<MemberDto> dtoList = new ArrayList<>();
			for (Member member : memberList) {
				MemberDto dto = new MemberDto();
				dto.setId(member.getId().toString());
				dto.setName(member.getDepartment().getOrgBelong().getName());
				
				dtoList.add(dto);
			}
			
			String[] ignore = {"isAgree","delay","isFinished","sort","isRead"};
			
			result = JsonUtil.succList(dtoList,ignore);
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}

}
