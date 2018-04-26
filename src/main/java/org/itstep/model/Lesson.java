package org.itstep.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table( name = "LESSONS" )
public class Lesson {

	@Id
	@Column( name = "LESSON_ID" )
	@GeneratedValue
	@JsonProperty
	private Integer id;
	
	@Column( name = "START_TIME" )
	@JsonProperty
	private Long startTime;
	
	@Column( name = "CABINET" )
	@JsonProperty
	private String cabinet;
		
	@ManyToOne( targetEntity = Subject.class )
	private Subject subject;
	
	@ManyToOne( targetEntity = Teacher.class )
	private Teacher teacher;
	
	@ManyToOne( targetEntity = Group.class )
	private Group group;

}
