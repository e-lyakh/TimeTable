package org.itstep.service.impl;

import java.util.List;

import org.itstep.dao.GroupDAO;
import org.itstep.model.Group;
import org.itstep.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService{

	@Autowired
	GroupDAO groupDao;
	
	public List<Group> findAllByCourse(String course) {
		return groupDao.findAllByCourse(course);
	}

	public Group save(Group group) {
		if(groupDao.getOne(group.getName()) == null)
		{
			return groupDao.save(group);
		}
		return null;
	}

	public Group update(Group group) {
		if(groupDao.getOne(group.getName()) != null)
		{
			return groupDao.save(group);
		}
		return null;
	}

	public Group get(String groupName) {
		return groupDao.getOne(groupName);
	}

	public void delete(Group group) {
		groupDao.delete(group);
	}
}
