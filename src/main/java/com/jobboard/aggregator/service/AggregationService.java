package com.jobboard.aggregator.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public CumulativeData computeCumulativeData(long univId, Date startTime, Date endTime) {
		
		CumulativeData cumulativeData = new CumulativeData();	
		HashMap<String, PositionSpecificData> positionCacheMap = new HashMap<String, PositionSpecificData>();
		HashMap<String, CompanySpecificData>  companyCacheMap = new HashMap<String, CompanySpecificData>();
		
		ArrayList<JobApplication> jobApplications = jobApplicationMapper.fetchAllApplicationByUnivInDateRange(univId, startTime, endTime);
		
		for(JobApplication currentApplication : jobApplications) {
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
			
			if(status.equals(JobStatusEnum.APPLIED)) {
				existingDataAboutPosition.addToAppliedCount();
				existingDataAboutCompany.addToAppliedCount();
			}
			else if(status.equals(JobStatusEnum.ASSESSMENT)) {
				existingDataAboutPosition.addToAssessmentCount();
				existingDataAboutCompany.addToAssessmentCount();
			}
			else if(status.equals(JobStatusEnum.INTERVIEW)) {
				existingDataAboutPosition.addToInterviewCount();
				existingDataAboutCompany.addToInterviewCount();
			}
			else if(status.equals(JobStatusEnum.SELECTED)) {
				existingDataAboutPosition.addToSelectedCount();
				existingDataAboutCompany.addToSelectedCount();
			}
			else if(status.equals(JobStatusEnum.REJECTED)) {
				existingDataAboutPosition.addToRejectCount();
				existingDataAboutCompany.addToRejectCount();
			}
			
		}
		
		cumulativeData.setPositionSpecificData(new ArrayList<PositionSpecificData>(positionCacheMap.values()));
		cumulativeData.setCompanySpecificData(new ArrayList<CompanySpecificData>(companyCacheMap.values()));
		
		return cumulativeData;
	}
}
