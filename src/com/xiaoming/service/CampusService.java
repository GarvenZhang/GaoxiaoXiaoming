package com.xiaoming.service;

import java.util.List;

import com.xiaoming.domain.Campus;

public interface CampusService {
	public Campus get(long id);
	public List<Campus> getByUniversity(long universityId);
}
