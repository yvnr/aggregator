package com.jobboard.aggregator.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobApplication {

    private Long id;
	 
	private long userId;
	
	private long univId;
	
	private String company;
	
	private String position;
	
	private String status; 
	
	private String jobId;
	
	private String location;
	
	private Date appliedTime;
	
	private Date assessmentTime;
	
	private Date interviewTime;
	
	private Date responseTime;
	
	
}
