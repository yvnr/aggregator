package com.jobboard.aggregator.model;

public class CompanySpecificData {
	
	private String company;
	private long appliedCount;
	private long assessmentCount;
	private long interviewCount;
	private long selectedCount;
	private long rejectCount;
	
	public CompanySpecificData() {
	}
	
	
	public CompanySpecificData(String company, long appliedCount, long assessmentCount, long interviewCount,
			long selectedCount, long rejectCount) {
		super();
		this.company = company;
		this.appliedCount = appliedCount;
		this.assessmentCount = assessmentCount;
		this.interviewCount = interviewCount;
		this.selectedCount = selectedCount;
		this.rejectCount = rejectCount;
	}

	public void addToAppliedCount() {
		this.appliedCount++;
	}
	
	public void addToAssessmentCount() {
		this.assessmentCount++;
	}
	
	public void addToInterviewCount() {
		this.interviewCount++;
	}
	
	public void addToSelectedCount() {
		this.selectedCount++;
	}
	
	public void addToRejectCount() {
		this.rejectCount++;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public long getAppliedCount() {
		return appliedCount;
	}

	public void setAppliedCount(long appliedCount) {
		this.appliedCount = appliedCount;
	}

	public long getAssessmentCount() {
		return assessmentCount;
	}

	public void setAssessmentCount(long assessmentCount) {
		this.assessmentCount = assessmentCount;
	}

	public long getInterviewCount() {
		return interviewCount;
	}

	public void setInterviewCount(long interviewCount) {
		this.interviewCount = interviewCount;
	}

	public long getSelectedCount() {
		return selectedCount;
	}

	public void setSelectedCount(long selectedCount) {
		this.selectedCount = selectedCount;
	}

	public long getRejectCount() {
		return rejectCount;
	}

	public void setRejectCount(long rejectCount) {
		this.rejectCount = rejectCount;
	}
	
	
}
