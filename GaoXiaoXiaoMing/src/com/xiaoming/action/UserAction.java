package com.xiaoming.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.JsonObject;
import com.xiaoming.base.Role;
import com.xiaoming.constants.Constants;
import com.xiaoming.constants.ErrorConstants;
import com.xiaoming.constants.SessionConstants;
import com.xiaoming.domain.Campus;
import com.xiaoming.domain.Member;
import com.xiaoming.domain.Organization;
import com.xiaoming.domain.User;
import com.xiaoming.dto.MemberDto;
import com.xiaoming.dto.RoleDto;
import com.xiaoming.dto.UserDetialsDto;
import com.xiaoming.dto.UserDto;
import com.xiaoming.util.IpUtil;
import com.xiaoming.util.JsonUtil;
import com.xiaoming.util.SendMessageUtil;
import com.xiaoming.util.StringUtil;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<UserDto> {

	private static final long serialVersionUID = 1L;

	/**
	 * 控制登陆的action url：/login.action
	 * 
	 * @return
	 * @throws Exception
	 */
	public String login() {
		try {
			//清空session
			removeUserFromSession();
			// 从json中获得model
			model = getModelFromJson();
			String loginName = model.getloginName();// 获取登录名
			String password = model.getPassword(); // 获取密码（经过加密的）


			// 判断非空
			if (null == loginName || "".equals(loginName)) {
				result = JsonUtil.fail(ErrorConstants.ACCOUNT);
				return ERROR;
			}
			if (null == password || "".equals(password)) {
				result = JsonUtil.fail(ErrorConstants.PASSWORD);
				return ERROR;
			}

			User user = userService.login(loginName, password);
			if (null != user) { // 为空说明密码或者用户名错误，没查到该用户
				if (!user.isValid()) { //该用户无效
					result = JsonUtil.fail(ErrorConstants.ACCOUNT_DELETED);
				}
				userService.updateLoginInfo(user, IpUtil.getIpAddr(request));
				//设置session
				setCurrentUserId(user.getId());
				setRole(user.getRole());

				UserDetialsDto userDto = new UserDetialsDto(user);
				if (user.getRole().equals(Role.ORG_USER)) {// 將默認社團設置爲當前的社團
					if(user.getDefaultMember()!=null){ //如果加了社团
						setCurrentMemberId(user.getDefaultMember().getId());
						setOrgId(user.getDefaultMember().getDepartment().getOrgBelong().getId());
					}
				} else if (user.getRole().equals(Role.ORG_ADMIN)) {
					setOrgId(user.getOrganization().getId());
				}
				result = JsonUtil.succObject(userDto);
			} else {
				result = JsonUtil.fail(ErrorConstants.ACPW);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 登出
	 * 
	 * @return
	 */
	public String logout() {
		if (session.get(SessionConstants.USER_ID) != null) {
			//清空session
			removeUserFromSession();
		}
		return "ui";
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public String updatePassword() {
		try {
			// 从json中获得model
			model = getModelFromJson();
			User user = userService.get(getCurrentUserId());
			String loginName = user.getloginName();// 获取登录名
			String password = model.getPassword(); // 获取密码（经过加密的）
			String newPassword = model.getNewPassword();// 新密码

			
			// 判断非空
			if (null == password || "".equals(password)) {
				result = JsonUtil.fail(ErrorConstants.PASSWORD);
				return ERROR;
			}
			if (null == newPassword || "".equals(newPassword)) {
				result = JsonUtil.fail(ErrorConstants.PASSWORD);
				return ERROR;
			}
			// 验证密码
			if (userService.checkPassword(getCurrentUserId(), password)) {
				userService.updatePassword(getCurrentUserId(), newPassword);
				result = JsonUtil.succ();
			} else {
				result = JsonUtil.fail(ErrorConstants.PASSWORD);
			}
			return SUCCESS;
		} catch (Exception e) {
			result = JsonUtil.fail();
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 注册
	 * 
	 * @return
	 */
	public String register_org() {
		try {
			model = getModelFromJson();

			// 验证验证码
			String code = (String) session.get("registerCode");
			if (code == null || model.getCode() == null || !code.equals(model.getCode())) {
				result = JsonUtil.fail(ErrorConstants.CODE);
				return ERROR;
			}
			//验证手机号码，是不是获取验证码的时候的手机号码
			String truePhone = (String) session.get("phoneNumber");
			if (model.getPhoneNumber() == null || "".equals(model.getPhoneNumber()) || truePhone == null
					|| "".equals(truePhone) || !truePhone.equals(model.getPhoneNumber())) {
				
				result = JsonUtil.fail(ErrorConstants.PHONE);
				return ERROR;
			}

			User user = new User();
			user.setLoginName(model.getloginName()); // 设置账号
			user.setPassword(model.getPassword()); // 设置密码
			user.setRegisterDate(new Date()); // 注册日期
			user.setLastLoginIP(IpUtil.getIpAddr(request)); // 最后登录ip
			user.setLastLoginTime(new Date()); // 最后登录时间
			user.setPhoneNumber(model.getPhoneNumber()); // 电话号码
			user.setRole(Role.ORG_ADMIN);
			
			//设置校区
			Campus campus = campusService.get(Long.parseLong(model.getCampusId()));
			user.setCampus(campus);
			
			Organization org = new Organization();
			org.setName(model.getOrgName());
			org.setContactPhoneNumber(model.getPhoneNumber());
			org.setCampus(campus);
			
			user = userService.register(user,org);

			result = JsonUtil.succ();
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 注册
	 * 
	 * @return
	 */
	public String register_user() {
		try {
			model = getModelFromJson();

			// 验证验证码
			String code = (String) session.get("registerCode");
			if (code == null || model.getCode() == null || !code.equals(model.getCode())) {
				result = JsonUtil.fail(ErrorConstants.CODE);
				return ERROR;
			}
			//验证手机号码，是不是获取验证码的时候的手机号码
			String truePhone = (String) session.get("phoneNumber");
			if (model.getPhoneNumber() == null || "".equals(model.getPhoneNumber()) || truePhone == null
					|| "".equals(truePhone) || !truePhone.equals(model.getPhoneNumber())) {
				result = JsonUtil.fail(ErrorConstants.CODE);
				return ERROR;
			}

			User user = new User();
			user.setLoginName(model.getloginName()); // 设置账号
			user.setPassword(model.getPassword()); // 设置密码
			user.setRegisterDate(new Date()); // 注册日期
			user.setLastLoginIP(IpUtil.getIpAddr(request)); // 最后登录ip
			user.setLastLoginTime(new Date()); // 最后登录时间
			user.setPhoneNumber(model.getPhoneNumber()); // 电话号码
			user.setRole(Role.ORG_USER);
			//设置校区
			Campus campus = campusService.get(Long.parseLong(model.getCampusId()));
			user.setCampus(campus);
			
			user = userService.register(user);

			result = JsonUtil.succ();
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}

	public String registerCode() {
		try {
			model = getModelFromJson();
			if(!StringUtil.isPhoneNumber(model.getPhoneNumber())){
				result = JsonUtil.fail(ErrorConstants.PHONE);
				return ERROR;
			}
			// 发送验证码
			String code = SendMessageUtil.sendCode(model.getPhoneNumber());
			// 将验证码和电话号码保存在session中，验证的时候用
			session.put("registerCode", code);
			session.put("phoneNumber", model.getPhoneNumber());

			Map<String, String> json = new HashMap<>();
			json.put("code", code);

			result = JsonUtil.succObject(json);
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String changeMember(){
		try{
			model = getModelFromJson();
			if(model.getId()==0){
				result = JsonUtil.fail("id is null");
				return ERROR;
			}
			Member member = memberService.get(model.getId());
			if(member.getUser().getId()!=getCurrentUserId()){ //不是自己加入的组织
				result = JsonUtil.fail(ErrorConstants.AUTHORITY);
				return ERROR;
			}
			setCurrentMember(member);
			setOrgId(member.getDepartment().getOrgBelong().getId());
			
			MemberDto dto = new MemberDto();
			dto.setOrganization(member.getDepartment().getOrgBelong().getName());
			dto.setName(member.getUser().getRealName());
			
			String[] ignore = {"delay","isAgree","isFinished","isRead","sort"};
			result = JsonUtil.succObject(dto, ignore);
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String getInfo(){
		try{
			User user = userService.get(getCurrentUserId());
			UserDto dto = new UserDto();
			dto.setId(user.getId());
			dto.setRealName(user.getRealName());
			
			result = JsonUtil.succObject(dto);
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}

}
