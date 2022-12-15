package com.jobboard.aggregator;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AggregatorApplication {
	
	@PostConstruct
	void setDefaultTimeZone() {
	    TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
	}
	public static void main(String[] args) {
		SpringApplication.run(AggregatorApplication.class, args);
	}

}
