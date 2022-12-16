package com.jobboard.aggregator.model;

import java.util.ArrayList;

public class PositionCumulativeData {
	
	private ArrayList<PositionSpecificData> positionSpecificData;
	
	public PositionCumulativeData() {
	}

	public PositionCumulativeData(ArrayList<PositionSpecificData> positionSpecificData) {
		super();
		this.positionSpecificData = positionSpecificData;
	}

	public ArrayList<PositionSpecificData> getPositionSpecificData() {
		return positionSpecificData;
	}

	public void setPositionSpecificData(ArrayList<PositionSpecificData> positionSpecificData) {
		this.positionSpecificData = positionSpecificData;
	}
}
