package com.jobboard.aggregator.model;

import java.util.ArrayList;
/**
 * A POJO class containing list of aggregated data per position in a given company
 * @author Nani
 *
 */
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
