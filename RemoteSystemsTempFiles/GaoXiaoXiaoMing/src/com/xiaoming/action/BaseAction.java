package com.xiaoming.action;


import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xiaoming.base.Role;
import com.xiaoming.constants.SessionConstants;
import com.xiaoming.domain.Member;
import com.xiaoming.dto.PageSupport;
import com.xiaoming.service.AbsenceService;
import com.xiaoming.service.ActivityService;
import com.xiaoming.service.AssignmentService;
import com.xiaoming.service.CampusService;
import com.xiaoming.service.DepartmentService;
import com.xiaoming.service.DocumentService;
import com.xiaoming.service.DynamicStateService;
import com.xiaoming.service.FolderService;
import com.xiaoming.service.GradeService;
import com.xiaoming.service.ImageService;
import com.xiaoming.service.JoinApplyService;
import com.xiaoming.service.MaterialService;
import com.xiaoming.service.MemberService;
import com.xiaoming.service.MessageService;
import com.xiaoming.service.OrganizationOperationService;
import com.xiaoming.service.OrganizationService;
import com.xiaoming.service.ProjectService;
import com.xiaoming.service.StatisticService;
import com.xiaoming.service.SystemMessageService;
import com.xiaoming.service.UniversityService;
import com.xiaoming.service.UnreadService;
import com.xiaoming.service.UserService;
import com.xiaoming.util.GetModelByJson;
import com.xiaoming.util.SystemContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Action基础类
 * @author Yunfei
 *
 * @param <T>
 */
public class BaseAction<T> extends ActionSupport implements 
		ModelDriven<T>,ServletRequestAware,ServletResponseAware,SessionAware{
	
	protected T model;
	
	private Class<T> clz;
	
	
	public BaseAction(){
		try{
			ParameterizedType pt = (ParameterizedType)this.getClass().getGenericSuperclass();
			clz = (Class<T>)pt.getActualTypeArguments()[0];
			model = clz.newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Properties validate;
	protected Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public T getModel() {
		return this.model;
	}
	
	/**
	 * 從request從獲取Json字符串	
	 * @return request中的字符串；出现异常的时候返回null
	 * @throws IOException 
	 */
	protected String getJsonFromRequest() throws IOException{
		return IOUtils.toString(request.getInputStream(),"utf-8");
	}
	
	protected T getModelFromRequestByGson() throws IOException{
		Gson gson = new Gson();
		return gson.fromJson(getJsonFromRequest(), clz);
	}
	
	/**
	 * 将json转换成model
	 * @return model
	 * @throws Exception
	 */
	protected T getModelFromJson() throws Exception {
		return GetModelByJson.getModel(request, model);
	}

	/**
	 * 将json转换成model
	 * @return model
	 * @throws Exception
	 */
	protected T getModelFromJson(Map<String, Class> classMap) throws Exception {
		return GetModelByJson.getModel(request, model, classMap);
	}
	
	protected T getModelFromJson(String json) throws Exception {
		return GetModelByJson.getModelByString(json, model);
	}
	
	protected T getModelFromJson(String json, Map<String, Class> classMap) throws Exception {
		return GetModelByJson.getModelByString(json, model, classMap);
	}
	
	protected Object getAttributeBySession(String key) {
		return session.get(key);
	}

	// 返回的json对象
	protected JSONObject result = new JSONObject();

	// 用于返回结果集
	public JSONObject getResult() {
		return result;
	}

	// 返回list 的json对象
	protected JSONArray resultList = new JSONArray();

	public JSONArray getResultList() {
		return resultList;
	}

	// 成功返回的list字符串
	protected final String SUCCESS_LIST = "successList";
	
	protected void write(String data) throws IOException{
		Writer out = response.getWriter();
		out.write(data);
		out.close();
	}
	
	/**
	 * 获取当前成员
	 * @return
	 */
	protected Member getCurrentMember(){
		 return (Member) session.get("currentMember");
	}
	
	protected void setCurrentMember(Member member){
		session.put("currentMember", member);
	}
	
	protected long getOrgId(){
		return (long)session.get(SessionConstants.ORG_ID);
	}
	
	protected void setOrgId(long orgId){
		session.put(SessionConstants.ORG_ID, orgId);
	}
		
	protected long getCurrentMemberId(){
		return (long)session.get(SessionConstants.MEMBER_ID);
	}
	
	protected void setCurrentMemberId(long memberId){
		session.put(SessionConstants.MEMBER_ID, memberId);
	}
	
	protected void setRole(Role role){
		session.put(SessionConstants.ROLE, role);
	}
	
	protected Role getRole(){
		return (Role)session.get(SessionConstants.ROLE);
	}
	
	protected long getCurrentUserId(){
		return (Long) session.get(SessionConstants.USER_ID);
	}
	
	protected void setCurrentUserId(long userId){
		session.put(SessionConstants.USER_ID, userId);
	}
	
	protected void removeUserFromSession(){
		session.remove(SessionConstants.USER_ID);
		session.remove(SessionConstants.MEMBER_ID);
		session.remove(SessionConstants.ORG_ID);
		session.remove(SessionConstants.ROLE);
	}
	
	// ============== 分页用的参数 =============
	protected int pageNum = 1; // 当前页
	protected int pageSize = 2; // 每页显示多少条记录

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setPageArgs(PageSupport page){
		SystemContext.setPageArgs(page);
	}
	
	@Resource
	UserService userService;
	@Resource
	AssignmentService assignmentService;
	@Resource
	ProjectService projectService;
	@Resource
	AbsenceService absenceService;
	@Resource
	OrganizationService organizationService;
	@Resource
	MemberService memberService;
	@Resource
	DynamicStateService dynamicStateService;
	@Resource
	ActivityService activityService;
	@Resource
	SystemMessageService systemMessageService;
	@Resource
	UnreadService unreadService;
	@Resource
	MaterialService materialService;
	@Resource
	StatisticService statisticService;
	@Resource
	ImageService imageService;
	@Resource
	UniversityService universityService;
	@Resource
	DocumentService documentService;
	@Resource
	FolderService folderService;
	@Resource
	CampusService campusService;
	@Resource
	OrganizationOperationService organizationOperationService;
	@Resource
	MessageService messageService;
	@Resource
	DepartmentService departmentService;
	@Resource
	GradeService gradeService;
	@Resource
	JoinApplyService joinApplyService;
}
