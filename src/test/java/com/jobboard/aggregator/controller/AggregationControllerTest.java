package com.jobboard.aggregator.controller;

import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;

import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.jobboard.aggregator.model.CompanyCumulativeData;
import com.jobboard.aggregator.model.PositionCumulativeData;
import com.jobboard.aggregator.service.AggregationService;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AggregationControllerTest {

	@Mock
	AggregationService aggregationService;
	 
	@InjectMocks
	AggregationController aggregationController;
	
	
	@Test
	void testGetCompanyAggregatedData() {
		when(aggregationService.computeCompanyCumulativeData(any(String.class), any(Date.class), any(Date.class))).thenReturn(new CompanyCumulativeData());
		ResponseEntity result = aggregationController.getCompanyAggregatedData(new Date(System.currentTimeMillis() - 10000), new Date(System.currentTimeMillis()), "user", "univ");
		assertEquals(200, result.getStatusCodeValue());
		
		when(aggregationService.computeCompanyCumulativeData(any(String.class), any(Date.class), any(Date.class))).thenThrow(RuntimeException.class);
		result = aggregationController.getCompanyAggregatedData(new Date(System.currentTimeMillis() - 10000), new Date(System.currentTimeMillis()), "user", "univ");
		assertEquals(500, result.getStatusCodeValue());
	}

	@Test
	void testGetPositionAggregateData() {
		when(aggregationService.computePositionCumulativeDataByCompany(any(String.class), any(String.class), any(Date.class), any(Date.class))).thenReturn(new PositionCumulativeData());
		ResponseEntity result = aggregationController.getPositionAggregateData(new Date(System.currentTimeMillis() - 10000), new Date(System.currentTimeMillis()), "Amazon",  "user", "univ");
		assertEquals(200, result.getStatusCodeValue());
		
		when(aggregationService.computePositionCumulativeDataByCompany(any(String.class), any(String.class),  any(Date.class), any(Date.class))).thenThrow(RuntimeException.class);
		result = aggregationController.getPositionAggregateData(new Date(System.currentTimeMillis() - 10000), new Date(System.currentTimeMillis()), "Amazon", "user", "univ");
		assertEquals(500, result.getStatusCodeValue());
	}

}
