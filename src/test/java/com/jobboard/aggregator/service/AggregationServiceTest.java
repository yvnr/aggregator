package com.jobboard.aggregator.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jobboard.aggregator.enums.JobStatusEnum;
import com.jobboard.aggregator.mapper.JobApplicationMapper;
import com.jobboard.aggregator.model.CompanyCumulativeData;
import com.jobboard.aggregator.model.JobApplication;
import com.jobboard.aggregator.model.PositionCumulativeData;
import com.sun.xml.bind.v2.schemagen.xmlschema.Any;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AggregationServiceTest {

	@Mock
	JobApplicationMapper jobApplicationMapper;
	
	@InjectMocks
	AggregationService aggregationService;
	
	@Test
	void testComputeCompanyCumulativeData() {
		JobApplication jobApplication = new JobApplication();
		jobApplication.setCompany("Amazon");
		jobApplication.setId(1L);
		jobApplication.setJobId("jobamxn123");
		jobApplication.setLocation("SFO");
		jobApplication.setPosition("SDE1");
		jobApplication.setStatus(JobStatusEnum.APPLIED.toString());
		jobApplication.setAppliedTime(new Date(System.currentTimeMillis()));
		jobApplication.setUnivId("university");
		jobApplication.setUserId("user");
		
		ArrayList<JobApplication> list = new ArrayList<JobApplication>();
		list.add(jobApplication);
		
		when(jobApplicationMapper.fetchAllApplicationByUnivInDateRange(any(String.class), any(Date.class), any(Date.class))).thenReturn(list);
		CompanyCumulativeData cumulativeData = aggregationService.computeCompanyCumulativeData("univ", new Date(System.currentTimeMillis() - 100000L), new Date(System.currentTimeMillis()));
		assertEquals(1, cumulativeData.getCompanySpecificData().size());
		
		jobApplication.setAssessmentTime(new Date(System.currentTimeMillis()));
		cumulativeData = aggregationService.computeCompanyCumulativeData("univ", new Date(System.currentTimeMillis() - 100000L), new Date(System.currentTimeMillis()));
		assertEquals(1, cumulativeData.getCompanySpecificData().size());
		
		jobApplication.setInterviewTime(new Date(System.currentTimeMillis()));
		cumulativeData = aggregationService.computeCompanyCumulativeData("univ", new Date(System.currentTimeMillis() - 100000L), new Date(System.currentTimeMillis()));
		assertEquals(1, cumulativeData.getCompanySpecificData().size());
		
		jobApplication.setStatus(JobStatusEnum.SELECTED.toString());
		cumulativeData = aggregationService.computeCompanyCumulativeData("univ", new Date(System.currentTimeMillis() - 100000L), new Date(System.currentTimeMillis()));
		assertEquals(1, cumulativeData.getCompanySpecificData().size());

		
	}

	@Test
	void testComputePositionCumulativeDataByCompany() {

		JobApplication jobApplication = new JobApplication();
		jobApplication.setCompany("Amazon");
		jobApplication.setId(1L);
		jobApplication.setJobId("jobamxn123");
		jobApplication.setLocation("SFO");
		jobApplication.setPosition("SDE1");
		jobApplication.setStatus(JobStatusEnum.APPLIED.toString());
		jobApplication.setAppliedTime(new Date(System.currentTimeMillis()));
		jobApplication.setUnivId("university");
		jobApplication.setUserId("user");
		
		ArrayList<JobApplication> list = new ArrayList<JobApplication>();
		list.add(jobApplication);
		
		when(jobApplicationMapper.fetchAllApplicationByUnivByCompanyInDateRange(any(String.class), any(String.class), any(Date.class), any(Date.class))).thenReturn(list);
		PositionCumulativeData cumulativeData = aggregationService.computePositionCumulativeDataByCompany("univ", "Amazon", new Date(System.currentTimeMillis() - 100000L), new Date(System.currentTimeMillis()));
		assertEquals(1, cumulativeData.getPositionSpecificData().size());
		
		jobApplication.setAssessmentTime(new Date(System.currentTimeMillis()));
		cumulativeData = aggregationService.computePositionCumulativeDataByCompany("univ", "Amazon", new Date(System.currentTimeMillis() - 100000L), new Date(System.currentTimeMillis()));
		assertEquals(1, cumulativeData.getPositionSpecificData().size());
		
		jobApplication.setInterviewTime(new Date(System.currentTimeMillis()));
		cumulativeData = aggregationService.computePositionCumulativeDataByCompany("univ", "Amazon",  new Date(System.currentTimeMillis() - 100000L), new Date(System.currentTimeMillis()));
		assertEquals(1, cumulativeData.getPositionSpecificData().size());
		
		jobApplication.setStatus(JobStatusEnum.SELECTED.toString());
		cumulativeData = aggregationService.computePositionCumulativeDataByCompany("univ", "Amazon",  new Date(System.currentTimeMillis() - 100000L), new Date(System.currentTimeMillis()));
		assertEquals(1, cumulativeData.getPositionSpecificData().size());

		
	
	}

}
