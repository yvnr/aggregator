package com.jobboard.aggregator.model;

import java.util.Date;

/**
 * A Java class containing all the information of a job application
 *
 */
public class JobApplication {

    private Long id;
	 
	private String userId;
	
	private String univId;
	
	private String company;
	
	private String position;
	
	private String status; 
	
	private String jobId;
	
	private String location;
	
	private Date appliedTime;
	
	private Date assessmentTime;
	
	private Date interviewTime;
	
	private Date responseTime;

	public JobApplication() {
	}
	
	public JobApplication(Long id, String userId, String univId, String company, String position, String status,
			String jobId, String location, Date appliedTime, Date assessmentTime, Date interviewTime,
			Date responseTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.univId = univId;
		this.company = company;
		this.position = position;
		this.status = status;
		this.jobId = jobId;
		this.location = location;
		this.appliedTime = appliedTime;
		this.assessmentTime = assessmentTime;
		this.interviewTime = interviewTime;
		this.responseTime = responseTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUnivId() {
		return univId;
	}

	public void setUnivId(String univId) {
		this.univId = univId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getAppliedTime() {
		return appliedTime;
	}

	public void setAppliedTime(Date appliedTime) {
		this.appliedTime = appliedTime;
	}

	public Date getAssessmentTime() {
		return assessmentTime;
	}

	public void setAssessmentTime(Date assessmentTime) {
		this.assessmentTime = assessmentTime;
	}

	public Date getInterviewTime() {
		return interviewTime;
	}

	public void setInterviewTime(Date interviewTime) {
		this.interviewTime = interviewTime;
	}

	public Date getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}
	
	
}
