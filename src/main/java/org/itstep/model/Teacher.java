package org.itstep.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table( name = "TEACHERS" )
public class Teacher extends User{
	
	@ManyToOne( targetEntity = Subject.class )
	private Subject subject;
}
