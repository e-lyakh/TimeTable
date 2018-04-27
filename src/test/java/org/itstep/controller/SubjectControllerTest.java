package org.itstep.controller;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.itstep.ApplicationRunner;
import org.itstep.model.Lesson;
import org.itstep.model.Subject;
import org.itstep.service.SubjectService;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SubjectControllerTest {

	private static Subject subject;

	@Autowired
	TestRestTemplate restTemplate;

	@MockBean
	SubjectService subjectService;

	@BeforeClass
	public static void setUp() throws Exception {
		
		subject = new Subject();
		subject.setName("Java");
		
	}

	@Test
	public void testSave() throws URISyntaxException {

		Mockito.when(subjectService.save(Mockito.any(Subject.class))).thenReturn(subject);

		RequestEntity<Subject> request = new RequestEntity<Subject>(subject, HttpMethod.POST, new URI("/subject"));
		ResponseEntity<Subject> response = restTemplate.exchange(request, Subject.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		Mockito.verify(subjectService, Mockito.times(1)).save(Mockito.any(Subject.class));

		assertEquals("Java", response.getBody().getName());

	}

	@Test
	public void testUpdate() throws URISyntaxException {
		
		Mockito.when(subjectService.update(Mockito.any(Subject.class))).thenReturn(subject);
		
		RequestEntity<Subject> request = new RequestEntity<Subject>(subject, HttpMethod.PUT, new URI("/subject"));		
		ResponseEntity<Subject> response = restTemplate.exchange(request, Subject.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		Mockito.verify(subjectService, Mockito.times(1)).update(Mockito.any(Subject.class));
		
	}

	@Test
	public void testGetOne() throws URISyntaxException {
		
		Mockito.when(subjectService.get(Mockito.anyString())).thenReturn(subject);

		HttpHeaders headers = new HttpHeaders();
		headers.set("name", subject.getName());
		
		RequestEntity request = new RequestEntity(headers, HttpMethod.GET, new URI("/subject/get-one/"));
		ResponseEntity<Subject> response = restTemplate.exchange(request, Subject.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		Mockito.verify(subjectService, Mockito.times(1)).get(Mockito.anyString());

		assertEquals("Java", response.getBody().getName());
		
	}

	@Test
	public void testDelete() throws URISyntaxException {

		Mockito.doNothing().when(subjectService).delete(Mockito.any(Subject.class));

		RequestEntity<Subject> request = new RequestEntity<Subject>(subject, HttpMethod.DELETE, new URI("/subject"));
		ResponseEntity response = restTemplate.exchange(request, Subject.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		Mockito.verify(subjectService, Mockito.times(1)).delete(Mockito.any(Subject.class));
	}

}
