package com.jobboard.aggregator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanySpecificData {
	
	private String company;
	private long appliedCount;
	private long assessmentCount;
	private long interviewCount;
	private long selectedCount;
	private long rejectCount;
	
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
}
