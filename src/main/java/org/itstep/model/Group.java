package org.itstep.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table( name = "GROUPS" )
public class Group {

	@Id
	@Column( name = "NAME" )
	@JsonProperty
	private String name;
	
	@Column( name = "COURSE" )
	@JsonProperty
	private String course;
	
	@Column( name = "SPECIALIZATION" )
	@JsonProperty
	private String specialization;
}
