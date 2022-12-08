package com.jobboard.aggregator.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
	
	@GetMapping("/analytics")
	public ResponseEntity getAggregatedData(@RequestParam(name = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
			@RequestParam(name = "end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
			@RequestHeader("X-user_id") long userId,
			@RequestHeader("X-univ_id") long univId) {
		
		try {
			
			CumulativeData cumulativeData = aggregationService.computeCumulativeData(univId, startDate, endDate);
			
			return new ResponseEntity(cumulativeData, HttpStatus.OK);
			
		}catch (Exception e) {
		}
		return null;
	}
	
}
