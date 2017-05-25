package com.xiaoming.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.xiaoming.base.BaseDao;
import com.xiaoming.constants.Constants;
import com.xiaoming.dao.CampusDao;
import com.xiaoming.dao.ImageDao;
import com.xiaoming.dao.InviteUrlDao;
import com.xiaoming.dao.MemberDao;
import com.xiaoming.dao.OrganizationDao;
import com.xiaoming.dao.ProjectDao;
import com.xiaoming.domain.Image;
import com.xiaoming.domain.InviteUrl;
import com.xiaoming.domain.Member;
import com.xiaoming.domain.Organization;
import com.xiaoming.domain.Pager;
import com.xiaoming.domain.Project;
import com.xiaoming.dto.ImageDto;
import com.xiaoming.dto.OrganizationDto;
import com.xiaoming.service.OrganizationOperationService;
import com.xiaoming.service.OrganizationService;
import com.xiaoming.util.MD5Util;
import com.xiaoming.util.qrcode.QRCodeUtils;

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

	@Resource
	MemberDao memberDao;
	@Resource
	OrganizationDao organizationDao;
	@Resource
	OrganizationOperationService organizationOperationService;
	@Resource
	ImageDao imageDao;
	@Resource
	CampusDao campusDao;
	@Resource
	BaseDao<Object> baseDao;
	@Resource
	ProjectDao projectDao;
	@Resource
	InviteUrlDao inviteUrlDao;

	@Override
	public Organization queryByMemberId(long memberId) {
		Member member = memberDao.get(memberId);
		return member.getDepartment().getOrgBelong();
	}

	@Override
	public Organization get(long id) {
		return organizationDao.get(id);
	}

	@Override
	public Organization update(OrganizationDto org, long orgId) {
		Organization newOrg = organizationDao.get(orgId);
		newOrg.setName(org.getName());
		newOrg.setContactName(org.getContactName());
		newOrg.setContactPhoneNumber(org.getPhone());
		newOrg.setIsPublic(org.isPublic());
		newOrg.setOfficeAddress(org.getOfficeAddress());
		newOrg.setOrgDescription(org.getOrgDescription());
		// logo
		String logoId = org.getLogoId();
		if (logoId != null && !"".equals(logoId)) {
			newOrg.setLogo(imageDao.get(Long.parseLong(logoId)));
		}
		// 校区
		String campusId = org.getCampusId();
		if (campusId != null && !"".equals(campusId)) {
			newOrg.setCampus(campusDao.get(Long.parseLong(campusId)));
		}
		// 操作日志
		String description = "修改了社团信息";
		organizationOperationService.add(newOrg, description);

		organizationDao.update(newOrg);
		return newOrg;
	}

	@Override
	public Pager<Organization> findByUniversity(long universityId) {
		String hql = "from Organization org where org.campus.university.id= :universId";
		Map<String, Object> alias = new HashMap<>();
		alias.put("universId", universityId);
		return organizationDao.findByAlias(hql, alias);
	}

	@Override
	public List<Organization> listByUniversity(long universityId) {
		String hql = "from Organization org where org.campus.university.id= :universId";
		Map<String, Object> alias = new HashMap<>();
		alias.put("universId", universityId);
		return organizationDao.list(hql, alias);
	}

	@Override
	public Organization queryByOrgId(long orgId) {
		return organizationDao.get(orgId);
	}

	@Override
	public List<String> getEdition(long orgId) {
		String hql = "select distinct edition "
				+ "from Member m "
				+ "where m.department.orgBelong.id= :orgId "
				+ "order by edition asc";
		Map<String, Object> alias = new HashMap<>();
		alias.put("orgId", orgId);
		List<Object> editions = baseDao.list(hql, alias);
		List<String> editionList = new ArrayList<>(editions.size());
		for (Object object : editions) {
			editionList.add((String) object);
		}
		return editionList;
	}

	@Override
	public void updateLogo(Image image,long orgId) {
		Organization org = organizationDao.get(orgId);
		org.setLogo(image);
		organizationDao.update(org);
	}

	@Override
	public List<Project> listProject(long orgId) {
		String hql = "from Project project where project.orgBelong.id="+orgId;
		return projectDao.list(hql, null,null);
	}

	@Override
	public ImageDto getJoinQRCode(long orgId) {
		/* 从已有的库中获取邀请信息
		 * 	若有，则用已有信息
		 * 	若无，则新建
		 */
		String hql = "from InviteUrl where organization.id="+orgId;
		Object obj = inviteUrlDao.queryObject(hql, null,null);
		InviteUrl inviteInfo = null;
		if(obj!=null){ //已有邀请链接信息
			inviteInfo = (InviteUrl)obj;
		}else{ //无
			String key = MD5Util.encode(orgId+""+new Date().getTime());
			inviteInfo = new InviteUrl();
			inviteInfo.setOrganization(organizationDao.get(orgId));
			inviteInfo.setKey(key);
			//生成二维码图片
			String fileName = "org_"+orgId+"_"+inviteInfo.getKey();
			WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
	        ServletContext servletContext = webApplicationContext.getServletContext();  
	        String inviteUrl = Constants.URL_JOIN_QRCODE+"?orgId="+orgId+"&key="+key;
	        
//			QRCodeUtils.createQRCodeIntoFile(contents, fileName);
		}
		return null;
	}

}
