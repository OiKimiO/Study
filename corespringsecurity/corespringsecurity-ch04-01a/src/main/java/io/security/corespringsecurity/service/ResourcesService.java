package io.security.corespringsecurity.service;

import java.util.List;

import io.security.corespringsecurity.domain.entity.Resources;

public interface ResourcesService {

	Resources selectResources(long id);
	
	List<Resources> selectResources();

	void insertResource(Resources resources);
	
	void deleteResources(long id);


}
