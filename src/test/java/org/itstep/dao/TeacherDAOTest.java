package org.itstep.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.itstep.ApplicationRunner;
import org.itstep.model.Subject;
import org.itstep.model.Teacher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TeacherDAOTest {

	private Subject subjectInDB;
	
	private Teacher teacherInDB_1;
	private Teacher teacherInDB_2;
	
	@Autowired
	SubjectDAO subjectDAO;
	
	@Autowired
	TeacherDAO teacherDAO;
	
	@Before
	public void setUpData(){
		
		Subject subject = new Subject();
		subject.setName("Delphi");
		subjectInDB = subjectDAO.save(subject);
		
		Teacher teacher1 = new Teacher();
		teacher1.setLogin("Ignatenko2207");
		teacher1.setPassword("123456");
		teacher1.setFirstName("Alex");;
		teacher1.setSecondName("Ignatenko");
		teacher1.setSubject(subjectInDB);
		teacherInDB_1 = teacherDAO.save(teacher1);
		
		Teacher teacher2 = new Teacher();
		teacher2.setLogin("Ignatenko2207_2");
		teacher2.setPassword("123456");
		teacher2.setFirstName("Alex_2");;
		teacher2.setSecondName("Ignatenko_2");
		teacher2.setSubject(subjectInDB);
		teacherInDB_2 = teacherDAO.save(teacher2);
		
	}
	
	@Test
	public void testFindAllBySubject() {
		
		List<Teacher> teachers = teacherDAO.findAllBySubject(subjectInDB.getName());
		
		assertNotNull(teachers);
		assertTrue(teachers != null);
		assertEquals(2, teachers.size());
		assertEquals(teachers.get(0).getSubject().getName(), "Delphi");
		
	}

	@After
	public void clearDB() {
		
		teacherDAO.delete(teacherInDB_1);
		teacherDAO.delete(teacherInDB_2);
		subjectDAO.delete(subjectInDB);
		
	}
}