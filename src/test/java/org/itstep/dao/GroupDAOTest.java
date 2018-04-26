package org.itstep.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.itstep.ApplicationRunner;
import org.itstep.model.Group;
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
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class GroupDAOTest {

	Group groupInDB_1;
	Group groupInDB_2;
	
	@Autowired
	GroupDAO groupDAO;
	
	@Before
	public void setUpData() {
		
		Group testGroup1 = new Group();		
		testGroup1.setName("B9P1_26");
		testGroup1.setSpecialization("Software development");
		testGroup1.setCourse("2");
		groupInDB_1 = groupDAO.save(testGroup1);
		
		Group testGroup2 = new Group();
		testGroup2.setName("B9P1_27");
		testGroup2.setCourse("2");
		testGroup2.setSpecialization("Design");
		groupInDB_2 = groupDAO.save(testGroup2);
		
	}
	
	@Test
	public void testFindAllByCourse() {
		
		List<Group> groups = groupDAO.findAllByCourse("2");
		
		assertNotNull(groups);
		assertEquals(2, groups.size());		
		assertTrue(groups.get(0).getSpecialization().startsWith("Software"));
		assertTrue(groups.get(1).getSpecialization().contains("Design"));
		
	}

	@After
	public void clearDB() {
		
		groupDAO.delete(groupInDB_1);
		groupDAO.delete(groupInDB_2);
		
	}

}
