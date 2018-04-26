package org.itstep.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table( name = "SUBJECTS" )
public class Subject {

	@Id
	@Column( name = "SUB_NAME" )
	@JsonProperty
	private String name;
	
}
