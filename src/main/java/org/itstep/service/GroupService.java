package org.itstep.service;

import java.util.List;

import org.itstep.model.Group;

public interface GroupService {

	Group save(Group group);
	
	Group update(Group group);
	
	Group get(String groupName);
	
	void delete(Group group);	
	
	List<Group> findAllByCourse(String course);
}
