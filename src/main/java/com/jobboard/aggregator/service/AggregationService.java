package com.jobboard.aggregator.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobboard.aggregator.controller.AggregationController;
import com.jobboard.aggregator.enums.JobStatusEnum;
import com.jobboard.aggregator.mapper.JobApplicationMapper;
import com.jobboard.aggregator.model.CompanySpecificData;
import com.jobboard.aggregator.model.CumulativeData;
import com.jobboard.aggregator.model.JobApplication;
import com.jobboard.aggregator.model.PositionSpecificData;

@Service
public class AggregationService {

	@Autowired
	JobApplicationMapper jobApplicationMapper;
	
	private final Logger logger = LogManager.getLogger(AggregationService.class);

	
	public CumulativeData computeCumulativeData(long univId, Date startTime, Date endTime) {
		
		CumulativeData cumulativeData = new CumulativeData();	
		HashMap<String, PositionSpecificData> positionCacheMap = new HashMap<String, PositionSpecificData>();
		HashMap<String, CompanySpecificData>  companyCacheMap = new HashMap<String, CompanySpecificData>();
		logger.debug("Attempting to fetch all applications from university id: " + univId + " from: " + startTime.toInstant() + " to: " + endTime.toInstant());
		
		ArrayList<JobApplication> jobApplications = jobApplicationMapper.fetchAllApplicationByUnivInDateRange(univId, startTime, endTime);
		
		logger.debug("Successfully fetched applications from university id: " + univId + " from: " + startTime.toInstant() + " to: " + endTime.toInstant());

		for(JobApplication currentApplication : jobApplications) {
			System.out.println(currentApplication.toString());
			String uniqueIdentifier = currentApplication.getCompany() + "," + currentApplication.getPosition();
			if(!positionCacheMap.containsKey(uniqueIdentifier)) {
				PositionSpecificData newPosition =  new PositionSpecificData();
				newPosition.setCompany(currentApplication.getCompany());
				newPosition.setPosition(currentApplication.getPosition());
				
				positionCacheMap.put(uniqueIdentifier, newPosition);
			}
			if(!companyCacheMap.containsKey(currentApplication.getCompany())) {
				CompanySpecificData newCompany = new CompanySpecificData();
				newCompany.setCompany(currentApplication.getCompany());
				
				companyCacheMap.put(currentApplication.getCompany(), newCompany);
			}
			PositionSpecificData existingDataAboutPosition = positionCacheMap.get(uniqueIdentifier);
			CompanySpecificData existingDataAboutCompany = companyCacheMap.get(currentApplication.getCompany());
			
			JobStatusEnum status = JobStatusEnum.valueOf(currentApplication.getStatus());
			Date appliedDate = currentApplication.getAppliedTime();
			Date assessmentDate = currentApplication.getAssessmentTime();
			Date interviewDate = currentApplication.getInterviewTime();
			
			System.out.println(appliedDate);
			System.out.println(assessmentDate);
			System.out.println(interviewDate);
			if(appliedDate != null && startTime.compareTo(appliedDate) <= 0 && endTime.compareTo(appliedDate) >= 0)
			{
				existingDataAboutPosition.addToAppliedCount();
				existingDataAboutCompany.addToAppliedCount();
			}
			if(assessmentDate != null && startTime.compareTo(assessmentDate) <= 0 && endTime.compareTo(assessmentDate) >= 0)
			{
				existingDataAboutPosition.addToAssessmentCount();
				existingDataAboutCompany.addToAssessmentCount();
			}
			if(interviewDate != null && startTime.compareTo(interviewDate) <= 0 && endTime.compareTo(interviewDate) >= 0)
			{
				existingDataAboutPosition.addToInterviewCount();
				existingDataAboutCompany.addToInterviewCount();
			}
			
			// last state so check based on status
			if(status.equals(JobStatusEnum.SELECTED)) {
				existingDataAboutPosition.addToSelectedCount();
				existingDataAboutCompany.addToSelectedCount();
			}
			else if(status.equals(JobStatusEnum.REJECTED)) {
				existingDataAboutPosition.addToRejectCount();
				existingDataAboutCompany.addToRejectCount();
			}
			
		}
		logger.info("The cumulative data prepared successfully");
		cumulativeData.setPositionSpecificData(new ArrayList<PositionSpecificData>(positionCacheMap.values()));
		cumulativeData.setCompanySpecificData(new ArrayList<CompanySpecificData>(companyCacheMap.values()));
		
		return cumulativeData;
	}
}
