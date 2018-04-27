package org.itstep.controller;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.itstep.ApplicationRunner;
import org.itstep.model.Subject;
import org.itstep.model.Teacher;
import org.itstep.service.TeacherService;
import org.junit.Before;
import org.junit.Ignore;
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
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TeacherControllerTest {
	
	private List<Teacher> teachers;
	
	@MockBean
	TeacherService teacherService;

	@Autowired
	TestRestTemplate restTemplate;	

	@Before
	public void setUp() throws Exception {
		
		teachers = new ArrayList<Teacher>();

		Subject subject = new Subject();
		subject.setName("Java");

		for (int i = 1; i <= 3; i++) {
			Teacher teacher = new Teacher();
			teacher.setLogin("teacher" + i);
			teacher.setPassword("123456" + i);
			teacher.setFirstName("Name");
			teacher.setSecondName("Surname");
			teacher.setSubject(subject);
			teachers.add(teacher);
		}
	}

	@Test
	public void testSave() throws URISyntaxException {
		
		Mockito.when(teacherService.save(Mockito.any(Teacher.class))).thenReturn(teachers.get(0));

		RequestEntity<Teacher> request = new RequestEntity<Teacher>(teachers.get(0), HttpMethod.POST,
				new URI("/teacher"));

		ResponseEntity<Teacher> response = restTemplate.exchange(request, Teacher.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		Mockito.verify(teacherService, Mockito.times(1)).save(Mockito.any(Teacher.class));
		
	}

	@Test
	public void testUpdate() throws URISyntaxException {
		
		Mockito.when(teacherService.update(Mockito.any(Teacher.class))).thenReturn(teachers.get(0));

		RequestEntity<Teacher> request = new RequestEntity<Teacher>(teachers.get(0), HttpMethod.PUT,
				new URI("/teacher"));

		ResponseEntity<Teacher> response = restTemplate.exchange(request, Teacher.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		Mockito.verify(teacherService, Mockito.times(1)).update(Mockito.any(Teacher.class));
		
	}

	@Test
	public void testGetOne() throws URISyntaxException {
		
		Mockito.when(teacherService.get(Mockito.anyString())).thenReturn(teachers.get(0));

		HttpHeaders headers = new HttpHeaders();
		headers.add("login", "teacher1");

		RequestEntity request = new RequestEntity(headers, HttpMethod.GET, new URI("/teacher/get-one"));
		ResponseEntity<Teacher> response = restTemplate.exchange(request, Teacher.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		Mockito.verify(teacherService, Mockito.times(1)).get(Mockito.anyString());
		
	}

	//@Ignore
	@Test
	public void testFindAllBySubject() throws URISyntaxException {
		
		Mockito.when(teacherService.findAllBySubject(Mockito.anyString())).thenReturn(teachers);

		HttpHeaders headers = new HttpHeaders();
		headers.add("name", "Java");

		RequestEntity request = new RequestEntity(headers, HttpMethod.GET, new URI("/teacher/get-by-subject"));
		ResponseEntity<List> response = restTemplate.exchange(request, List.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		Mockito.verify(teacherService, Mockito.times(1)).findAllBySubject(Mockito.anyString());

		assertEquals(3, response.getBody().size());
		
	}

	@Test
	public void testDelete() throws URISyntaxException {
		Mockito.doNothing().when(teacherService).delete(Mockito.any(Teacher.class));

		RequestEntity<Teacher> request = new RequestEntity<Teacher>(teachers.get(0), HttpMethod.DELETE,
				new URI("/teacher"));

		ResponseEntity<HttpStatus> response = restTemplate.exchange(request, HttpStatus.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		Mockito.verify(teacherService, Mockito.times(1)).delete(Mockito.any(Teacher.class));
	}

}
