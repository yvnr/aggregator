package com.jobboard.aggregator.model;

/**
 * The class contains all the aggregated information of job applications per position made to a specific company
 *	
 */
public class PositionSpecificData {


	private String company; // name of the company
	private String position;	// position title
	private long appliedCount;	// number of job applications to position in APPLIED status
	private long assessmentCount; // number of job applications to position in ASSESSMENT status
	private long interviewCount; // number of job applications to position in INTERVIEW status
	private long selectedCount; // number of job applications to position in SELECTED status
	private long rejectCount; // number of job applications to position in REJECT status
	
	public PositionSpecificData() {
	}
	
	
	
	public PositionSpecificData(String company, String position, long appliedCount, long assessmentCount,
			long interviewCount, long selectedCount, long rejectCount) {
		super();
		this.company = company;
		this.position = position;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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
