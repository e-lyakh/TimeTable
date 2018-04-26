package org.itstep.service;

import org.itstep.model.Subject;

public interface SubjectService {

	Subject save(Subject subject);
	
	Subject update(Subject subject);
	
	Subject get(String name);
	
	void delete(Subject subject);
}
