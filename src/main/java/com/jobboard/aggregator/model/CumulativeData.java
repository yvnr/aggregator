package com.jobboard.aggregator.model;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CumulativeData {

	private ArrayList<CompanySpecificData> companySpecificData;
	private ArrayList<PositionSpecificData> positionSpecificData;

	public CumulativeData() {
		positionSpecificData = new ArrayList<PositionSpecificData>();
		companySpecificData = new ArrayList<CompanySpecificData>();
	}

}
