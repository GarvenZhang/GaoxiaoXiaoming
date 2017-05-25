package com.xiaoming.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.xiaoming.domain.Campus;
import com.xiaoming.domain.University;
import com.xiaoming.util.JsonIgnore;


public class UniversityDto {
	private String id;
	private String name;
	private ArrayList<CampusDto> campusList;
	private CampusDto campus;
	
	public UniversityDto(){
		
	}
	
	public UniversityDto(Campus campus){
		this.setCampus(new CampusDto(campus));
		this.setId(campus.getUniversity().getId().toString());
		this.setName(campus.getUniversity().getName());
		this.setCampusList(null);
	}
	
	public UniversityDto(University university){
		this.setId(university.getId().toString());
		this.setName(university.getName());
		
		ArrayList<CampusDto> campusList = new ArrayList<>();
		for (Campus campus : university.getCampus()) {
			campusList.add(new CampusDto(campus));
		}
		
		this.setCampusList(campusList);
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	

	public ArrayList<CampusDto> getCampusList() {
		return campusList;
	}

	public void setCampusList(ArrayList<CampusDto> campusList) {
		this.campusList = campusList;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the campus
	 */
	@JsonIgnore
	public CampusDto getCampus() {
		return campus;
	}
	/**
	 * @param campus the campus to set
	 */
	public void setCampus(CampusDto campus) {
		this.campus = campus;
	}
	
}
