package org.itstep.service.impl;

import org.itstep.dao.SubjectDAO;
import org.itstep.model.Subject;
import org.itstep.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService{
	
	@Autowired
	SubjectDAO subjectDao;

	public Subject save(Subject subject) {
		if(subjectDao.getOne(subject.getName()) == null) {
			return subjectDao.save(subject);
		}
		return null;
	}

	public Subject update(Subject subject) {
		if(subjectDao.getOne(subject.getName()) != null) {
			return subjectDao.save(subject);
		}
		return null;
	}

	public Subject get(String name) {
		return subjectDao.getOne(name);
	}

	public void delete(Subject subject) {
		subjectDao.delete(subject);
	}

}
