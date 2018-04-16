package com.healthrecord.mobile.model;

import java.io.Serializable;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRawValue;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfileM implements Serializable {

	private String id;
	private UserIndicatorM[] indicators;
	
//	@JsonRawValue
//	private String indicatorDoc;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public UserIndicatorM[] getIndicators() {
		return indicators;
	}
	public void setIndicators(UserIndicatorM[] indicators) {
		this.indicators = indicators;
	}
	@Override
	public String toString() {
		return "UserProfileM [id=" + id + ", indicators=" + Arrays.toString(indicators) + "]";
	}
	
//	@JsonRawValue
//	public String getIndicatorDoc() {
//		return indicatorDoc;
//	}
//	public void setIndicatorDoc(String indicatorDoc) {
//		this.indicatorDoc = indicatorDoc;
//	}
	
	
}
