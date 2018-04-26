package org.itstep.service;

import java.util.List;

import org.itstep.model.Teacher;

public interface TeacherService {

	Teacher save(Teacher teacher);

	Teacher update(Teacher teacher);

	Teacher get(String login);

	void delete(Teacher teacher);

	List<Teacher> findAllBySubject(String subject);
}
