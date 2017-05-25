package com.xiaoming.service;

import java.util.List;

import com.xiaoming.domain.Member;
import com.xiaoming.domain.Organization;
import com.xiaoming.domain.User;
import com.xiaoming.dto.UserInfoDto;

/**
 * 用户服务接口
 * @author Yunfei
 *
 */
public interface UserService {
	/**
	 * 登陆
	 * @param loginName 登录名
	 * @param password	密码
	 * @return			登陆的用户，失败返回null
	 */
	public User login(String loginName,String password, String code);
	/**
	 * 不用验证码的登陆
	 * @param loginName
	 * @param password
	 * @return
	 */
	public User login(String loginName, String password);
	/**
	 * 更新最新的登陆ip和时间
	 * @param user
	 * @param ip
	 */
	public void updateLoginInfo(User user,String ip);
	/**
	 * 更新密码
	 * @param user
	 * @param newPassword
	 * @param code
	 */
	public void updatePassword(User user,String newPassword);
	/**
	 * 更新id为“id”的用户的密码
	 * @param id
	 * @param newPassword
	 */
	public void updatePassword(long id,String newPassword);
	/**
	 * 检验密码
	 * @param loginName
	 * @param password
	 * @param code
	 * @return
	 */
	public boolean checkPassword(String loginName,String password,String code);
	public boolean checkPassword(long id,String password);
	/**
	 * 获取用户
	 * @return
	 */
	public User get(long id);
	/**
	 * 修改用户详细信息
	 * @param userInfoDto
	 * @param id
	 * @return
	 */
	public Member update(UserInfoDto userInfoDto,Member currentMember);
	public Member update(UserInfoDto userInfoDto,long memberId);
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	public User register(User user);
	/**
	 * 注册组织
	 * @param user
	 * @param org
	 * @return
	 */
	public User register(User user,Organization org);
	/**
	 * 通过电话号码判断该用户是否存在
	 * @param phoneNumber
	 * @return
	 */
	public boolean isExist(String phoneNumber);
	/**
	 * 通过手机号码获取用户
	 * @param phone
	 * @return
	 */
	public User getByPhone(String phone);
	/**
	 * 获取用户参加的所有组织
	 * @return
	 */
	public List<Member> getMembers(long userId);
}
