package org.itstep.controller;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.itstep.ApplicationRunner;
import org.itstep.model.Group;
import org.itstep.service.GroupService;
import org.junit.After;
import org.junit.Before;
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
public class GroupControllerTest {

	private List<Group> groups;
	
	@MockBean
	GroupService groupService;

	@Autowired
	TestRestTemplate restTemplate;	
	
	@Before
	public void setUp() throws Exception {
		
		groups = new ArrayList<Group>();
		
		for (int i = 1; i <= 3; i++) {
			Group group = new Group();
			group.setName("B9P1_26" + i);
			group.setSpecialization("Java");
			group.setCourse("2");

			groups.add(group);
		}
		
	}	

	@Test
	public void testSave() throws URISyntaxException {
		
		Mockito.when(groupService.save(Mockito.any(Group.class))).thenReturn(groups.get(0));

		RequestEntity<Group> request = new RequestEntity<Group>(groups.get(0), HttpMethod.POST, new URI("/group"));

		ResponseEntity<Group> response = restTemplate.exchange(request, Group.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		Mockito.verify(groupService, Mockito.times(1)).save(Mockito.any(Group.class));
		
	}

	@Test
	public void testUpdate() throws URISyntaxException {
		
		Mockito.when(groupService.update(Mockito.any(Group.class))).thenReturn(groups.get(0));

		RequestEntity<Group> request = new RequestEntity<Group>(groups.get(0), HttpMethod.PUT, new URI("/group"));

		ResponseEntity<Group> response = restTemplate.exchange(request, Group.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		Mockito.verify(groupService, Mockito.times(1)).update(Mockito.any(Group.class));
		
	}

	@Test
	public void testGetOne() throws URISyntaxException {
		
		Mockito.when(groupService.get(Mockito.anyString())).thenReturn(groups.get(0));

		HttpHeaders headers = new HttpHeaders();
		headers.add("name", "ST21");
		
		RequestEntity request = new RequestEntity(headers, HttpMethod.GET, new URI("/group/get-one"));
		ResponseEntity<Group> response = restTemplate.exchange(request, Group.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		Mockito.verify(groupService, Mockito.times(1)).get(Mockito.anyString());
		
	}

	@Test
	public void testFindAllByCourse() throws URISyntaxException {
		
		Mockito.when(groupService.findAllByCourse(Mockito.anyString())).thenReturn(groups);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("course", "1");
		
		RequestEntity request = new RequestEntity(headers, HttpMethod.GET, new URI("/group/get-by-course"));
		ResponseEntity<List> response = restTemplate.exchange(request, List.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		Mockito.verify(groupService, Mockito.times(1)).findAllByCourse(Mockito.anyString());
		
		assertEquals(3, response.getBody().size());
		
	}

	@Test
	public void testDelete() throws URISyntaxException {
		
		Mockito.doNothing().when(groupService).delete(Mockito.any(Group.class));
		
		RequestEntity<Group> request = new RequestEntity<Group>(groups.get(0), HttpMethod.DELETE, new URI("/group"));

		ResponseEntity<HttpStatus> response = restTemplate.exchange(request, HttpStatus.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		Mockito.verify(groupService, Mockito.times(1)).delete(Mockito.any(Group.class));
		
	}	

}
