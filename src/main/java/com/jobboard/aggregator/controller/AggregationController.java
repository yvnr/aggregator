package com.jobboard.aggregator.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobboard.aggregator.model.CumulativeData;
import com.jobboard.aggregator.service.AggregationService;


@RestController
public class AggregationController {

	
	@Autowired
	AggregationService aggregationService;
	
	private final Logger logger = LogManager.getLogger(AggregationController.class);
	
	@GetMapping("/analytics")
	public ResponseEntity getAggregatedData(@RequestParam(name = "start") @DateTimeFormat(iso = ISO.DATE) Date startDate,
			@RequestParam(name = "end") @DateTimeFormat(iso = ISO.DATE) Date endDate,
			@RequestHeader("X-user_id") long userId,
			@RequestHeader("X-univ_id") long univId) {
		
		try {

			logger.info("Recieved request to fetch cumulative data from: " + startDate.toInstant() + " to: " + endDate.toInstant());
			CumulativeData cumulativeData = aggregationService.computeCumulativeData(univId, startDate, endDate);
			logger.debug("Responding to request to fetch cumulative data from: " + startDate.toInstant() + " to: " + endDate.toInstant() + " with: " + cumulativeData.toString() );
			return new ResponseEntity(cumulativeData, HttpStatus.OK);
			
		}catch (Exception ex) {
			logger.error("Error occured while fetching cumulative from: " + startDate.toInstant() + " to: " + endDate.toInstant() + " The error is: " + ex.getMessage());
			HashMap<String, Object> errorMap = new HashMap();
			errorMap.put("errorMessage", ex.getMessage());
			errorMap.put("timeStamp", LocalDateTime.now());
			
			return new ResponseEntity(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
