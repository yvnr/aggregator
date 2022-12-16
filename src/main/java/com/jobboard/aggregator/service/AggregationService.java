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
import com.jobboard.aggregator.model.CompanyCumulativeData;
import com.jobboard.aggregator.model.JobApplication;
import com.jobboard.aggregator.model.PositionCumulativeData;
import com.jobboard.aggregator.model.PositionSpecificData;

/**
 * The service layer class contains the business logic to compute the aggregated date as required. The instance methods of this class are called by controller layer methods.
 *
 */
@Service
public class AggregationService {

	@Autowired
	JobApplicationMapper jobApplicationMapper;
	
	private final Logger logger = LogManager.getLogger(AggregationService.class);

	/**
	 * The method computes the aggregated data of job applications made by all the users from a specific university
	 * @param univId The unique id of the university/school
	 * @param startTime The beginning of the date range
	 * @param endTime The end of the date range
	 * @return returns the aggregated data
	 */
	public CompanyCumulativeData computeCompanyCumulativeData(String univId, Date startTime, Date endTime) {
		
		CompanyCumulativeData cumulativeData = new CompanyCumulativeData();
		HashMap<String, CompanySpecificData>  companyCacheMap = new HashMap<String, CompanySpecificData>();
		logger.debug("Attempting to fetch all applications from university id: " + univId + " from: " + startTime.toInstant() + " to: " + endTime.toInstant());
		
		ArrayList<JobApplication> jobApplications = jobApplicationMapper.fetchAllApplicationByUnivInDateRange(univId, startTime, endTime);
		
		logger.debug("Successfully fetched applications from university id: " + univId + " from: " + startTime.toInstant() + " to: " + endTime.toInstant());

		for(JobApplication currentApplication : jobApplications) {
			String uniqueIdentifier = currentApplication.getCompany() + "," + currentApplication.getPosition();
			
			if(!companyCacheMap.containsKey(currentApplication.getCompany())) {
				CompanySpecificData newCompany = new CompanySpecificData();
				newCompany.setCompany(currentApplication.getCompany());
				
				companyCacheMap.put(currentApplication.getCompany(), newCompany);
			}
			CompanySpecificData existingDataAboutCompany = companyCacheMap.get(currentApplication.getCompany());
			
			JobStatusEnum status = JobStatusEnum.valueOf(currentApplication.getStatus());
			Date appliedDate = currentApplication.getAppliedTime();
			Date assessmentDate = currentApplication.getAssessmentTime();
			Date interviewDate = currentApplication.getInterviewTime();
			
			if(appliedDate != null && startTime.compareTo(appliedDate) <= 0 && endTime.compareTo(appliedDate) >= 0)
			{
				existingDataAboutCompany.addToAppliedCount();
			}
			if(assessmentDate != null && startTime.compareTo(assessmentDate) <= 0 && endTime.compareTo(assessmentDate) >= 0)
			{
				existingDataAboutCompany.addToAssessmentCount();
			}
			if(interviewDate != null && startTime.compareTo(interviewDate) <= 0 && endTime.compareTo(interviewDate) >= 0)
			{
				existingDataAboutCompany.addToInterviewCount();
			}
			
			// last state so check based on status
			if(status.equals(JobStatusEnum.SELECTED)) {
				existingDataAboutCompany.addToSelectedCount();
			}
			else if(status.equals(JobStatusEnum.REJECTED)) {
				existingDataAboutCompany.addToRejectCount();
			}
			
		}
		logger.debug("The cumulative data for university: {} prepared successfully", univId);
		cumulativeData.setCompanySpecificData(new ArrayList<CompanySpecificData>(companyCacheMap.values()));
		
		return cumulativeData;
	}
	
	/**
	 * The method computes the aggregated data of job applications made by all the users from a specific university to a specific company in the given date range
	 * @param univId The unique id of the university/school
	 * @param company The name of the company
	 * @param startDate The beginning of the date range
	 * @param endDate The end of the date range
	 * @return returns the aggregated data
	 */
	public PositionCumulativeData computePositionCumulativeDataByCompany(String univId, String company, Date startDate, Date endDate) {
		
		HashMap<String, PositionSpecificData> positionCacheMap = new HashMap<String, PositionSpecificData>();
		PositionCumulativeData positionCumulativeData = new PositionCumulativeData();
		
		ArrayList<JobApplication> jobApplications = jobApplicationMapper.fetchAllApplicationByUnivByCompanyInDateRange(univId, company, startDate, endDate);
		logger.debug("Successfully fetched {} applications to company: {} from university id: " + univId + " from: " + startDate.toInstant() + " to: " + endDate.toInstant(), jobApplications.size(), company);

		for(JobApplication currentApplication : jobApplications) {
			String uniqueIdentifier = currentApplication.getCompany() + "," + currentApplication.getPosition();
			if(!positionCacheMap.containsKey(uniqueIdentifier)) {
				PositionSpecificData newPosition =  new PositionSpecificData();
				newPosition.setCompany(currentApplication.getCompany());
				newPosition.setPosition(currentApplication.getPosition());
				
				positionCacheMap.put(uniqueIdentifier, newPosition);
			}
			
			PositionSpecificData existingDataAboutPosition = positionCacheMap.get(uniqueIdentifier);
			
			JobStatusEnum status = JobStatusEnum.valueOf(currentApplication.getStatus());
			Date appliedDate = currentApplication.getAppliedTime();
			Date assessmentDate = currentApplication.getAssessmentTime();
			Date interviewDate = currentApplication.getInterviewTime();
			
			if(appliedDate != null && startDate.compareTo(appliedDate) <= 0 && endDate.compareTo(appliedDate) >= 0)
			{
				existingDataAboutPosition.addToAppliedCount();
			}
			if(assessmentDate != null && startDate.compareTo(assessmentDate) <= 0 && endDate.compareTo(assessmentDate) >= 0)
			{
				existingDataAboutPosition.addToAssessmentCount();
			}
			if(interviewDate != null && startDate.compareTo(interviewDate) <= 0 && endDate.compareTo(interviewDate) >= 0)
			{
				existingDataAboutPosition.addToInterviewCount();
			}
			
			// last state so check based on status
			if(status.equals(JobStatusEnum.SELECTED)) {
				existingDataAboutPosition.addToSelectedCount();
			}
			else if(status.equals(JobStatusEnum.REJECTED)) {
				existingDataAboutPosition.addToRejectCount();
			}
			
		}
		logger.debug("The cumulative data for university: {} prepared successfully", univId);

		positionCumulativeData.setPositionSpecificData(new ArrayList<PositionSpecificData>(positionCacheMap.values()));

		return positionCumulativeData;
		
	}
}
