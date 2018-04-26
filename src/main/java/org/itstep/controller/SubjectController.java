package org.itstep.controller;

import org.itstep.model.Subject;
import org.itstep.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/subject")
public class SubjectController {

	@Autowired
	SubjectService subjectService;

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	ResponseEntity<Subject> save(@RequestBody Subject subject) {
		Subject savedSubject = subjectService.save(subject);
		if (savedSubject != null) {
			return new ResponseEntity<Subject>(savedSubject, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}

	@PutMapping(consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	ResponseEntity<Subject> update(@RequestBody Subject subject) {
		Subject savedSubject = subjectService.update(subject);
		if (savedSubject != null) {
			return new ResponseEntity<Subject>(savedSubject, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(path = "/get-one", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	ResponseEntity<Subject> getOne(@RequestHeader String name) {
		Subject subject = subjectService.get(name);
		if (subject != null) {
			return new ResponseEntity<Subject>(subject, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	ResponseEntity delete(@RequestBody Subject subject) {
		subjectService.delete(subject);
		return new ResponseEntity(HttpStatus.OK);
	}

}
