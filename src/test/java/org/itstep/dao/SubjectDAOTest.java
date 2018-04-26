package org.itstep.dao;

import static org.junit.Assert.*;

import org.itstep.ApplicationRunner;
import org.itstep.model.Subject;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class SubjectDAOTest {

	Subject subjectInDB;

	@Autowired
	SubjectDAO subjectDAO;

	@Test
	public void testSave() {

		Subject subject = new Subject();
		subject.setName("Java");		
		subjectInDB = subjectDAO.save(subject);
		
		assertNotNull(subjectInDB);
		assertEquals("Java", subjectInDB.getName());
		
	}

	@After
	public void clearDB() {
		subjectDAO.delete(subjectInDB);
	}

}