package org.itstep.controller;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.itstep.ApplicationRunner;
import org.itstep.model.Group;
import org.itstep.model.Lesson;
import org.itstep.model.Subject;
import org.itstep.model.Teacher;
import org.itstep.service.LessonService;
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
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LessonControllerTest {

	@MockBean
	LessonService lessonService;

	@Autowired
	TestRestTemplate restTemplate;

	private List<Lesson> lessons;
	private Subject subject;
	private Teacher teacher;
	private Group group;

	@Before
	public void setUp() throws Exception {
		
		lessons = new ArrayList<Lesson>();
		Subject subject = new Subject();
		Teacher teacher = new Teacher();
		Group group = new Group();

		for (int i = 1; i <= 3; i++) {
			Lesson lesson = new Lesson();
			lesson.setSubject(subject);
			lesson.setTeacher(teacher);
			lesson.setCabinet("16");
			lesson.setGroup(group);
			lesson.setStartTime((long) 45 * i);
			lessons.add(lesson);
		}
	}

	@Test
	public void testSave() throws URISyntaxException {
		
		Mockito.when(lessonService.save(Mockito.any(Lesson.class))).thenReturn(lessons.get(0));

		RequestEntity<Lesson> request = new RequestEntity<Lesson>(lessons.get(0), HttpMethod.POST, new URI("/lesson"));

		ResponseEntity<Lesson> response = restTemplate.exchange(request, Lesson.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		Mockito.verify(lessonService, Mockito.times(1)).save(Mockito.any(Lesson.class));
		
	}

	@Test
	public void testUpdate() throws URISyntaxException {
		
		Mockito.when(lessonService.update(Mockito.any(Lesson.class))).thenReturn(lessons.get(0));

		RequestEntity<Lesson> request = new RequestEntity<Lesson>(lessons.get(0), HttpMethod.PUT, new URI("/lesson"));

		ResponseEntity<Lesson> response = restTemplate.exchange(request, Lesson.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		Mockito.verify(lessonService, Mockito.times(1)).update(Mockito.any(Lesson.class));
		
	}

	@Test
	public void testGetOne() throws URISyntaxException {
		
		Mockito.when(lessonService.get(Mockito.anyInt())).thenReturn(lessons.get(0));

		HttpHeaders headers = new HttpHeaders();
		headers.add("id", "1");

		RequestEntity request = new RequestEntity(headers, HttpMethod.GET, new URI("/lesson/get-one"));
		ResponseEntity<Lesson> response = restTemplate.exchange(request, Lesson.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		Mockito.verify(lessonService, Mockito.times(1)).get(Mockito.anyInt());
		
	}

	//@Ignore
	@Test
	public void testfindAllByStartTime() throws URISyntaxException {
		
		Mockito.when(lessonService.findAllByStartTime(Mockito.anyLong(), Mockito.anyLong())).thenReturn(lessons);

		HttpHeaders headers = new HttpHeaders();
		headers.add("start", "123456789");
		headers.add("end", "123654987");

		RequestEntity request = new RequestEntity(headers, HttpMethod.GET, new URI("/lesson/get-by-period"));
		ResponseEntity<List> response = restTemplate.exchange(request, List.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		Mockito.verify(lessonService, Mockito.times(1)).findAllByStartTime(Mockito.anyLong(), Mockito.anyLong());

		assertEquals(3, response.getBody().size());
	}

	@Test
	public void testDelete() throws URISyntaxException {
		
		Mockito.doNothing().when(lessonService).delete(Mockito.any(Lesson.class));

		RequestEntity<Lesson> request = new RequestEntity<Lesson>(lessons.get(0), HttpMethod.DELETE,
				new URI("/lesson"));

		ResponseEntity<HttpStatus> response = restTemplate.exchange(request, HttpStatus.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		Mockito.verify(lessonService, Mockito.times(1)).delete(Mockito.any(Lesson.class));
	}

}
