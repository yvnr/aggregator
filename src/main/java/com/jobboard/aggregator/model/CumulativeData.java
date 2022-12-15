package com.jobboard.aggregator.model;

import java.util.ArrayList;


public class CumulativeData {

	private ArrayList<CompanySpecificData> companySpecificData;
	private ArrayList<PositionSpecificData> positionSpecificData;

	public CumulativeData() {
		positionSpecificData = new ArrayList<PositionSpecificData>();
		companySpecificData = new ArrayList<CompanySpecificData>();
	}

	public ArrayList<CompanySpecificData> getCompanySpecificData() {
		return companySpecificData;
	}

	public void setCompanySpecificData(ArrayList<CompanySpecificData> companySpecificData) {
		this.companySpecificData = companySpecificData;
	}

	public ArrayList<PositionSpecificData> getPositionSpecificData() {
		return positionSpecificData;
	}

	public void setPositionSpecificData(ArrayList<PositionSpecificData> positionSpecificData) {
		this.positionSpecificData = positionSpecificData;
	}
	
	
	
}
