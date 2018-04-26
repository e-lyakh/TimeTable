package org.itstep.controller;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.itstep.ApplicationRunner;
import org.itstep.model.Group;
import org.itstep.model.Student;
import org.itstep.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

	private List<Student> students = new ArrayList();

	@MockBean
	StudentService studentService;

	@Autowired
	TestRestTemplate restTemplate;

	@Before
	public void setUp() throws Exception {
		
		Group gr = new Group();
		gr.setName("B9P1_26");
		gr.setSpecialization("Software development");
		gr.setCourse("year course");

		for (int i = 1; i <= 3; i++) {
			Student student = new Student();

			student.setLogin("Ignatenko" + i);
			student.setPassword("123456");
			student.setFirstName("Alex" + i);
			student.setSecondName("Ignatenko");
			student.setGroup(gr);

			students.add(student);
		}
	}

	@Test
	public void testSave() {
	}

	@Ignore
	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOne() throws URISyntaxException {
		Student oneStudent = students.get(0);
		Mockito.when(studentService.get(Mockito.anyString())).thenReturn(oneStudent);

		HttpHeaders headers = new HttpHeaders();
		headers.add("login", oneStudent.getLogin());
		
		RequestEntity<String> request = new RequestEntity<String>(headers, HttpMethod.GET,
				new URI("/student/get-one"));
		ResponseEntity<Student> response = restTemplate.exchange(request, Student.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		Mockito.verify(studentService, Mockito.times(1)).get(Mockito.anyString());

	}

	@Test
	public void testFindAllByGroup() throws URISyntaxException {
		
		Mockito.when(studentService.findAllByGroup(Mockito.anyString())).thenReturn(students);

		HttpHeaders headers = new HttpHeaders();
		headers.add("groupName", "B9P1_26");
		
		RequestEntity<String> request = new RequestEntity<String>(headers, HttpMethod.GET,
				new URI("/student/get-by-group"));
		ResponseEntity<List> response = restTemplate.exchange(request, List.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		Mockito.verify(studentService, Mockito.times(1)).findAllByGroup(Mockito.anyString());
		
		assertEquals(3, response.getBody().size());
	}

	@Test
	public void testDelete() {
	}

}
