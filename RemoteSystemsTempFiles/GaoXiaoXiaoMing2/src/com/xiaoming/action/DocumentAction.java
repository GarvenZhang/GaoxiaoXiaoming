package com.xiaoming.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xiaoming.domain.Document;
import com.xiaoming.domain.User;
import com.xiaoming.dto.DocumentDto;
import com.xiaoming.util.CharacterUtil;
import com.xiaoming.util.JsonUtil;
import com.xiaoming.util.SavePic;

@Controller
@Scope("prototype")
public class DocumentAction extends MediaBaseAction<DocumentDto> {

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;

	private Integer _public = 0;
	
	public Integer get_public() {
		return _public;
	}

	public void set_public(Integer _public) {
		this._public = _public;
	}

	/***
	 * 增加文件 /org_user/document_add.action
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		try {
			if(_public!=0&&_public!=1){
				result = JsonUtil.fail();
				return ERROR;
			}
			model = getModel();
			if (getOrgId() == 0l) {
				result = JsonUtil.fail();
				return ERROR;
			}
			String path = ServletActionContext.getServletContext().getRealPath(
					getSavePath());
			String type = getFileFileName().split("\\.")[1];
			// logo文件路径
			String filePath = path + "\\" + getFileFileName();
			boolean isSave = SavePic.saveFile(filePath, file);
			if (isSave) {
				Document document = new Document();
				document.setName(getFileFileName());
				document.setFileType(type);
				document.setUrl(getSavePath());
				document.setOrganization(organizationService.get(getOrgId()));
				document.setUploadTime(new Date());
				document.setFolder(folderService.get(model.getFloderId()));
				document.setIsPublic(_public==0?false:true);
				documentService.save(document);
				//设置上传用户
				User user = userService.get(getCurrentUserId());
				document.setUploader(user);
				
				result = JsonUtil.succObject(new DocumentDto(document));
			} else {
				result = JsonUtil.fail();
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}

	/***
	 * 
	 * @return
	 * @throws Exception
	 */
	public String download() throws Exception {
		Document document = null;
		try {
			model = getModelFromJson();
			document = documentService.get(model.getId());
			this.fileName = CharacterUtil.changeCharacter(document.getName(),
					"ISO8859-1");
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream in = new FileInputStream(ServletActionContext
					.getServletContext().getRealPath("") + document.getUrl()+"\\"+document.getName());
			int c = -1;
			while ((c = in.read()) != -1) {
				out.write(c);
			}
			stream = new ByteArrayInputStream(out.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		if (document.getFileType().contains("doc")) {
			return "word";
		} else if (document.getFileType().contains("xls")) {
			return "excel";
		} else if (document.getFileType().contains("ppt")) {
			return "ppt";
		} else if (document.getFileType().equals("txt")) {
			return "txt";
		} else {
			return ERROR;
		}

	}

	public String delete() throws Exception {
		try {
			model = getModelFromJson();
			documentService.delete(model.getId());
			result = JsonUtil.succ();
		} catch (Exception e) {
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String edit()throws Exception{
		try{
			model = getModelFromJson();
			Document document = documentService.get(model.getId());
			document.setName(model.getName());
			document.setIsPublic(model.getIsPublic());
			documentService.update(document);
			result = JsonUtil.succObject(new DocumentDto(document),"organization");
		}catch(Exception e){
			e.printStackTrace();
			result = JsonUtil.fail();
			return ERROR;
		}
		return SUCCESS;
	}

}
