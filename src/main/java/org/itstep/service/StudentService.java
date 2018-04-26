package org.itstep.service;

import java.util.List;

import org.itstep.model.Student;

public interface StudentService {

	Student save(Student student);
	
	Student update(Student student);
	
	Student get(String login);
	
	List<Student> findAllByGroup(String groupName);
	
	void delete(Student student);
}
