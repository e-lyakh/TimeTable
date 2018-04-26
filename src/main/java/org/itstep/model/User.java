package org.itstep.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public class User {

	@Id
	@Column(name ="LOGIN")
	@JsonProperty
	private String login;
	
	@Column(name ="PASSWORD")
	@JsonProperty
	private String password;
	
	@Column(name ="FIRST_NAME")
	@JsonProperty
	private String firstName;
	
	@Column(name ="SECOND_NAME")
	@JsonProperty
	private String secondName;
	
}
