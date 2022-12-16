package com.jobboard.aggregator.model;

import java.util.ArrayList;

/**
 * A POJO class having a list of the aggregated data per company
 *
 */
public class CompanyCumulativeData {

	private ArrayList<CompanySpecificData> companySpecificData;

	
	public CompanyCumulativeData() {
		companySpecificData = new ArrayList<CompanySpecificData>();
	}
	
	

	public CompanyCumulativeData(ArrayList<CompanySpecificData> companySpecificData,
			ArrayList<PositionSpecificData> positionSpecificData) {
		super();
		this.companySpecificData = companySpecificData;
	}



	public ArrayList<CompanySpecificData> getCompanySpecificData() {
		return companySpecificData;
	}

	public void setCompanySpecificData(ArrayList<CompanySpecificData> companySpecificData) {
		this.companySpecificData = companySpecificData;
	}
	
}
