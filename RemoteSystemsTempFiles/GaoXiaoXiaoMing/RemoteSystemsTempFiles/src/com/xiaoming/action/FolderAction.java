/**
 * @author zmj
 * 处理文挡文件夹的action
 * 
 */
package com.xiaoming.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xiaoming.domain.Document;
import com.xiaoming.domain.Folder;
import com.xiaoming.domain.Organization;
import com.xiaoming.dto.DocumentDto;
import com.xiaoming.dto.FolderDto;
import com.xiaoming.util.JsonUtil;

@Controller
@Scope("prototype") 
public class FolderAction extends BaseAction<Folder> {

	private Long parentId = 0l; // 是否为顶层文件，只能为1获取0值
	private Integer i_p; // 是否为公开的文件夹

	public Integer getI_p() {
		return i_p;
	}

	public void setI_p(Integer i_p) {
		this.i_p = i_p;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 返回顶级公开文件夹列表 /org_user/folder_openList.action
	 * 
	 * @return
	 */
	public String openList() throws Exception {
		// TODO 还要检查用户是否是这个组织的成员
		try {
			Long orgId = getOrgId();
			Integer isTop = parentId == 0l ? 1 : 0;
			if (orgId == 0l) {
				result = JsonUtil.fail();
				return ERROR;
			}
			List<FolderDto> folderDtos = new ArrayList<>();
			List<Folder> folders = null;
			// 是否为顶层文件夹
			if (isTop == 0) {
				if (i_p == 0) {
					// 非公开文件夹
					folders = folderService.findList(parentId, false, false);
				} else if (i_p == 1) {
					// 公开文件夹
					folders = folderService.findList(parentId, true, false);
				} else {
					result = JsonUtil.fail();
					return ERROR;
				}

			} else if (isTop == 1) {
				if (i_p == 0) {
					// 非公开文件夹
					folders = folderService.findList(orgId, false, true);
				} else if (i_p == 1) {
					// 公开文件夹
					folders = folderService.findList(orgId, true, true);
				} else {
					result = JsonUtil.fail();
					return ERROR;
				}
			} else {
				result = JsonUtil.fail();
				return ERROR;
			}

			if (parentId == 0l) {
				for (Folder f : folders) {
					f.setDocuments(null);
					folderDtos.add(new FolderDto(f));
				}
				result = JsonUtil.succList(folderDtos);
			} else {
				Folder f = folders.get(0);
				ArrayList<DocumentDto> documentDtos = new ArrayList<>();
				for (Folder f1 : f.getChilders()) {
					folderDtos.add(new FolderDto(f1));
				}
				for (Document d : f.getDocuments()) {
					documentDtos.add(new DocumentDto(d));
				}
				JSONObject childerJson = JsonUtil
						.succList(new ArrayList<FolderDto>(folderDtos));
				JSONObject documentsJson = JsonUtil
						.succList(new ArrayList<DocumentDto>(documentDtos));
				String str = childerJson.toString().substring(8,
						childerJson.toString().length() - 17);
				String str2 = documentsJson.toString().substring(8,
						documentsJson.toString().length() - 17);
				result = JSONObject.fromObject("{data:{folders:" + str
						+ ",documents:" + str2 + "},status:true}");
				// 防止缓存
				parentId = 0l;
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}

	/***
	 * 增加文件夹
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		try {
			model = getModelFromJson();
			Organization organization = organizationService.get(getOrgId());
			Folder parent = folderService.get(model.getParent().getId());
			model.setOrganization(organization);
			model.setParent(parent);
			folderService.save(model);
			result = JsonUtil.succObject(new FolderDto(model));
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail();
		}
		return SUCCESS;
	}

	/***
	 * 删除文件夹
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		try {
			if (getOrgId() == 0l) {
				result = JsonUtil.fail();
				return ERROR;
			}
			folderService.delete(folderService.get(model.getId()));
			result = JsonUtil.succ();
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}

	public String edit() throws Exception {
		try {
			model = getModelFromJson();
			Folder folder = folderService.get(model.getId());

			folder.setName(model.getName());

			folder.setIsPublic(model.getIsPublic());
			folderService.update(folder);
			result = JsonUtil.succObject(new FolderDto(folder), "organization",
					"user");
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}

}
