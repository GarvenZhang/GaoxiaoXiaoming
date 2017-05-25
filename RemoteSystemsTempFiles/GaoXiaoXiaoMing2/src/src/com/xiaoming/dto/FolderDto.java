package com.xiaoming.dto;

import java.util.ArrayList;
import java.util.Date;

import com.xiaoming.domain.Document;
import com.xiaoming.domain.Folder;

public class FolderDto {
	private Long id;
	private String name;
	private Date date;
	private OrganizationDto organization;
	private Boolean avariable;
	private ArrayList<DocumentDto> documents;
	
	public FolderDto(Folder folder){
		this.id = folder.getId();
		this.name = folder.getName();
		this.date = folder.getDate();
		this.organization = new OrganizationDto(folder.getOrganization());
		this.avariable = folder.getAvariable();
		if(null!=folder.getDocuments()&&folder.getDocuments().size()>0){
			ArrayList<Document> ds = new ArrayList<>(folder.getDocuments());
			documents = new ArrayList<>();
			for(Document d:ds){
				documents.add(new DocumentDto(d));
			}
		}
	}
	
	public ArrayList<DocumentDto> getDocuments() {
		return documents;
	}

	public void setDocuments(ArrayList<DocumentDto> documents) {
		this.documents = documents;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public OrganizationDto getOrganization() {
		return organization;
	}
	public void setOrganization(OrganizationDto organization) {
		this.organization = organization;
	}
	public Boolean getAvariable() {
		return avariable;
	}
	public void setAvariable(Boolean avariable) {
		this.avariable = avariable;
	}

	
}
